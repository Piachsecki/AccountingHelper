package com.piasecki.service;

import com.piasecki.constants.CurrencyConstants;
import com.piasecki.domain.Invoice;
import com.piasecki.domain.Receipt;
import com.piasecki.domain.User;
import com.piasecki.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class VatCalculatorImpl implements VatCalculator {
    private final InvoiceService invoiceService;
    private final UserService userService;
    private final ReceiptService receiptService;

    @Override
    public BigDecimal calculateVat(LocalDate specifiedDate) {
        User currentUser = SecurityUtils.getCurrentUser(userService);
        if (!currentUser.getEntrepreneurship().isVat()){
            log.error("Current user: [{}] is not VAT payer. isVat: [{}]", currentUser.getUsername(), currentUser.getEntrepreneurship().isVat());
            throw new RuntimeException("You are not a VAT payer!");
        }

        List<Invoice> allIncomeInvoices = invoiceService.getAllIncomeInvoicesByDate(specifiedDate);
        List<Invoice> allCostInvoices = invoiceService.getAllCostInvoicesByDate(specifiedDate);
        List<Receipt> allReceipts = receiptService.getAllReceiptsByDate(specifiedDate)
                .stream()
                .filter(Receipt::isNip)
                .collect(Collectors.toList());


        BigDecimal vatToPay = vatToPay(allIncomeInvoices);
        BigDecimal vatToClaim = vatToClaim(allCostInvoices, allReceipts);

        return vatToPay.add(vatToClaim);
    }

    private BigDecimal vatToPay(List<Invoice> allIncomeInvoices) {
        BigDecimal vatToPay = BigDecimal.ZERO;
        for (Invoice incomeInvoice : allIncomeInvoices) {
            vatToPay = vatToPay.subtract(incomeInvoice.getPrice().getAmount().multiply(incomeInvoice.getTaxRate()));
        }
        return vatToPay;
    }

    //TODO Tutaj trzeba zaimplementowac klase CurrencyConverter i uzywac jej w Revenue/Income/VatCalculatorach.
    private BigDecimal vatToClaim(List<Invoice> allCostInvoices, List<Receipt> allReceipts) {
        BigDecimal vatToClaim = BigDecimal.ZERO;
        for (Invoice costInvoice : allCostInvoices) {
            if(Objects.isNull(costInvoice.getPrice().getAmount()) || Objects.isNull(costInvoice.getTaxRate())){
                log.error("Tried to add an invoice with amount [{}] or taxRate [{}] set as null.",
                        costInvoice.getPrice().getAmount(),
                        costInvoice.getTaxRate());
                continue;
            }

            //Przyjmuje, poki co, ze invoice sa w pln
            vatToClaim = vatToClaim.add(costInvoice.getPrice().getAmount().multiply(costInvoice.getTaxRate()));
        }

        for (Receipt receipt : allReceipts) {
            if(receipt.getPrice().getCurrency().equals("PLN") &&
                    receipt.getPrice().getAmount().compareTo(BigDecimal.valueOf(100).multiply(CurrencyConstants.EUR)) >= 1) {
                vatToClaim.add(receipt.getPrice().getAmount().multiply(receipt.getTaxRate()));
            }
        }


        return vatToClaim;
    }
}

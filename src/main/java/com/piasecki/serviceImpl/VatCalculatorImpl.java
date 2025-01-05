package com.piasecki.serviceImpl;

import com.piasecki.constants.CurrencyConstants;
import com.piasecki.domain.Invoice;
import com.piasecki.domain.Receipt;
import com.piasecki.domain.User;
import com.piasecki.service.InvoiceService;
import com.piasecki.service.ReceiptService;
import com.piasecki.service.UserService;
import com.piasecki.service.VatCalculator;
import com.piasecki.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class VatCalculatorImpl implements VatCalculator {
    private InvoiceService invoiceService;
    private UserService userService;
    private ReceiptService receiptService;

    @Override
    public BigDecimal calculateVat() {
        User currentUser = SecurityUtils.getCurrentUser(userService);
        if (!currentUser.getEntrepreneurship().isVat()){
            throw new RuntimeException("You are not a VAT payer!");
        }

        List<Invoice> allIncomeInvoices = invoiceService.getAllIncomeInvoices();
        List<Invoice> allCostInvoices = invoiceService.getAllCostInvoices();
        List<Receipt> allReceipts = receiptService.getAllReceipts()
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

    private BigDecimal vatToClaim(List<Invoice> allCostInvoices, List<Receipt> allReceipts) {
        BigDecimal vatToClaim = BigDecimal.ZERO;
        for (Invoice costInvoice : allCostInvoices) {
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

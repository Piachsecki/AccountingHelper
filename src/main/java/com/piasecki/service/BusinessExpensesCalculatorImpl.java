package com.piasecki.service;

import com.piasecki.domain.Invoice;
import com.piasecki.domain.Receipt;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessExpensesCalculatorImpl implements BusinessExpensesCalculator {
    private final InvoiceService invoiceService;
    private final ReceiptService receiptService;

    @Override
    public BigDecimal calculateBusinessExpenses() {
        BigDecimal businessExpenses = BigDecimal.ZERO;

        List<Invoice> allCostInvoices = invoiceService.getAllCostInvoices();
        for (Invoice costInvoice : allCostInvoices) {
            businessExpenses = businessExpenses.add(costInvoice.getPrice().getAmount());
        }

        List<Receipt> allReceipts = receiptService.getAllReceipts();
        for (Receipt receipt : allReceipts) {
            businessExpenses = businessExpenses.add(receipt.getPrice().getAmount());
        }

        return businessExpenses;

    }
}

package com.piasecki.serviceImpl;

import com.piasecki.domain.Invoice;
import com.piasecki.domain.Receipt;
import com.piasecki.service.BusinessExpensesCalculator;
import com.piasecki.service.InvoiceService;
import com.piasecki.service.ReceiptService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class BusinessExpensesCalculatorImpl implements BusinessExpensesCalculator {
    private InvoiceService invoiceService;
    private ReceiptService receiptService;

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

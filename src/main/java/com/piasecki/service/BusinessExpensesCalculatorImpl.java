package com.piasecki.service;

import com.piasecki.domain.Invoice;
import com.piasecki.domain.Receipt;
import com.piasecki.domain.Worker;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessExpensesCalculatorImpl implements BusinessExpensesCalculator {
    private final InvoiceService invoiceService;
    private final ReceiptService receiptService;
    private final WorkerService workerService;

    @Override
    public BigDecimal calculateBusinessExpenses(LocalDate specifiedDate) {
        BigDecimal businessExpenses = BigDecimal.ZERO;

        List<Invoice> allCostInvoices = invoiceService.getAllCostInvoicesByDate(specifiedDate);
        for (Invoice costInvoice : allCostInvoices) {
            businessExpenses = businessExpenses.add(costInvoice.getPrice().getAmount());
        }

        List<Receipt> allReceipts = receiptService.getAllReceiptsByDate(specifiedDate);
        for (Receipt receipt : allReceipts) {
            businessExpenses = businessExpenses.add(receipt.getPrice().getAmount());
        }

        List<Worker> allWorkers = workerService.getAllWorkers();
        for (Worker worker : allWorkers) {
            businessExpenses = businessExpenses.add(worker.getSalary());
        }
        return businessExpenses.negate();

    }
}

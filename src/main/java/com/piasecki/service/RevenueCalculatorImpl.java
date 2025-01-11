package com.piasecki.service;

import com.piasecki.domain.Invoice;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RevenueCalculatorImpl implements RevenueCalculator {
    private final InvoiceService invoiceService;
    @Override
    public BigDecimal calculateRevenue() {
        BigDecimal revenue = BigDecimal.ZERO;
        List<Invoice> allIncomeInvoices = invoiceService.getAllIncomeInvoices();
        for (Invoice incomeInvoice : allIncomeInvoices) {
            revenue = revenue.add(incomeInvoice.getPrice().getAmount());
        }
        return revenue;
    }
}

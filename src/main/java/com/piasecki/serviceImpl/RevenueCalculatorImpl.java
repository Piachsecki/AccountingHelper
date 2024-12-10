package com.piasecki.serviceImpl;

import com.piasecki.domain.Invoice;
import com.piasecki.service.InvoiceService;
import com.piasecki.service.RevenueCalculator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Service
public class RevenueCalculatorImpl implements RevenueCalculator {
    private InvoiceService invoiceService;
    @Override
    public BigDecimal calculateRevenue(List<Invoice> invoices) {
        return null;
    }
}

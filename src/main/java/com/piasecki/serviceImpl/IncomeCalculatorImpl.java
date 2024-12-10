package com.piasecki.serviceImpl;


import com.piasecki.domain.Invoice;
import com.piasecki.domain.Receipt;
import com.piasecki.service.IncomeCalculator;
import com.piasecki.service.InvoiceService;
import com.piasecki.service.ReceiptService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Service
public class IncomeCalculatorImpl implements IncomeCalculator {
    private InvoiceService invoiceService;
    private ReceiptService receiptService;

    @Override
    public BigDecimal calculateIncome(List<Invoice> invoices, List<Receipt> receipts) {
        return null;
    }
}

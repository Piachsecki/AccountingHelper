package com.piasecki.serviceImpl;


import com.piasecki.domain.Invoice;
import com.piasecki.domain.Receipt;
import com.piasecki.service.IncomeCalculator;
import com.piasecki.service.InvoiceService;
import com.piasecki.service.ReceiptService;
import com.piasecki.service.UserService;
import com.piasecki.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class IncomeCalculatorImpl implements IncomeCalculator {
    private InvoiceService invoiceService;
    private ReceiptService receiptService;
//    private ReceiptService receiptService;
    private UserService userService;

    @Override
    public BigDecimal calculateIncome() {
        return BigDecimal.ZERO;
    }


}

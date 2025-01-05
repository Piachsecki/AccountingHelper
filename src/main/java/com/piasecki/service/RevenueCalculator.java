package com.piasecki.service;

import com.piasecki.domain.Invoice;
import com.piasecki.domain.Receipt;

import java.math.BigDecimal;
import java.util.List;

public interface RevenueCalculator {
    BigDecimal calculateRevenue();
}

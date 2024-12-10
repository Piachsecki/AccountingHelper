package com.piasecki.serviceImpl;

import com.piasecki.domain.Entrepreneurship;
import com.piasecki.service.IncomeCalculator;
import com.piasecki.service.TaxCalculatorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class LumpSumTaxCalculator implements TaxCalculatorService {
    private IncomeCalculator incomeCalculator;

    @Override
    public BigDecimal calculateIncomeTax(BigDecimal amount, Entrepreneurship entrepreneurship) {
        return null;
    }
}

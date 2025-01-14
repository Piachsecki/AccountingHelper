package com.piasecki.service;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

//TODO tutaj wzorzec projektowy strategii ale wlasnie jak ?
@Service
@Data
@RequiredArgsConstructor
public class ScaleTaxCalculationStrategy implements TaxCalculationStrategy {
    private final IncomeCalculator incomeCalculator;



    @Override
    public BigDecimal calculateIncomeTax() {
        BigDecimal income = incomeCalculator.calculateIncome();
        if (income.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }

        BigDecimal taxRate = (income.compareTo(BigDecimal.valueOf(10000)) >= 0)
                ? BigDecimal.valueOf(0.32)
                : BigDecimal.valueOf(0.17);

        return income.multiply(taxRate).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}

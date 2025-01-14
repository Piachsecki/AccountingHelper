package com.piasecki.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Data
public class FlatTaxCalculationStrategy implements TaxCalculationStrategy {
    private final IncomeCalculator incomeCalculator;
    @Override
    public BigDecimal calculateIncomeTax() {
        BigDecimal income = incomeCalculator.calculateIncome();
        if (income.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf(0.19).multiply(income);
    }
}


/*
   Faktorka by dzialala, i nie byloby nulla w incomeCalculator, gdybys uzyl tutaj uzyl RequiredARgsConstructor

   Wtedy on wstrzykiwal nulla w miejsce incomeCalculatora A GDYBYS uzyl RequiredArgsConstructor i POLE MUSI BYC FINALNE,
   TO WTEDY wstrzyknal by normalnie serwis
 */

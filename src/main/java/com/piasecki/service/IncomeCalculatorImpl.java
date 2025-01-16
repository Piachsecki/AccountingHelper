package com.piasecki.service;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class IncomeCalculatorImpl implements IncomeCalculator {
    private final RevenueCalculator revenueCalculator;
    private final BusinessExpensesCalculator businessExpensesCalculator;

    @Override
    public BigDecimal calculateIncome(LocalDate specifiedDate) {
        BigDecimal revenue = revenueCalculator.calculateRevenue(specifiedDate);
        BigDecimal businessExpenses = businessExpensesCalculator.calculateBusinessExpenses(specifiedDate);
        return revenue.subtract(businessExpenses);
    }

}



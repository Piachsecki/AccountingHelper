package com.piasecki.service;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class IncomeCalculatorImpl implements IncomeCalculator {
    private final RevenueCalculator revenueCalculator;
    private final BusinessExpensesCalculator businessExpensesCalculator;

    @Override
    public BigDecimal calculateIncome() {
        BigDecimal revenue = revenueCalculator.calculateRevenue();
        BigDecimal businessExpenses = businessExpensesCalculator.calculateBusinessExpenses();
        return revenue.subtract(businessExpenses);
    }

}



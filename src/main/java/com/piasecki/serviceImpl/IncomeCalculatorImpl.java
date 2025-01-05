package com.piasecki.serviceImpl;


import com.piasecki.domain.Invoice;
import com.piasecki.domain.Receipt;
import com.piasecki.service.*;
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
    private RevenueCalculator revenueCalculator;
    private BusinessExpensesCalculator businessExpensesCalculator;

    @Override
    public BigDecimal calculateIncome() {
        BigDecimal revenue = revenueCalculator.calculateRevenue();
        BigDecimal businessExpenses = businessExpensesCalculator.calculateBusinessExpenses();
        return revenue.subtract(businessExpenses);
    }


}

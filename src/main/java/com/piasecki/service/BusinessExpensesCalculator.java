package com.piasecki.service;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface BusinessExpensesCalculator {
    BigDecimal calculateBusinessExpenses(LocalDate specifiedDate);
}

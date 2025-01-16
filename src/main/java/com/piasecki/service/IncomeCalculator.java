package com.piasecki.service;

import com.piasecki.domain.Invoice;
import com.piasecki.domain.Receipt;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface IncomeCalculator {
    BigDecimal calculateIncome(LocalDate specifiedDate);
}
/*
SERWIS I JEGO IMPLEMENTACJA MUSI BYC W TYM SAMYM PAKIECIE!!!!!
 */
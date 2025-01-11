package com.piasecki.service;

import com.piasecki.domain.Invoice;
import com.piasecki.domain.Receipt;

import java.math.BigDecimal;
import java.util.List;

public interface IncomeCalculator {
    BigDecimal calculateIncome();
}
/*
SERWIS I JEGO IMPLEMENTACJA MUSI BYC W TYM SAMYM PAKIECIE!!!!!
 */
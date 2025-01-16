package com.piasecki.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

public interface VatCalculator {
    BigDecimal calculateVat(LocalDate specifiedDate);
}

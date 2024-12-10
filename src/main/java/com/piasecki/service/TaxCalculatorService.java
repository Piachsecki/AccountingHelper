package com.piasecki.service;

import com.piasecki.domain.Entrepreneurship;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface TaxCalculatorService {
    BigDecimal calculateIncomeTax(BigDecimal amount, Entrepreneurship entrepreneurship);
}

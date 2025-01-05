package com.piasecki.serviceImpl;

import com.piasecki.domain.Entrepreneurship;
import com.piasecki.service.TaxCalculationStrategy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TaxCalculatorService {
    public BigDecimal calculateTax(Entrepreneurship entrepreneurship) {
        TaxCalculationStrategy taxCalculationStrategy = TaxCalculatedStrategyFactory.create(entrepreneurship);
       return taxCalculationStrategy.calculateIncomeTax();

    }
}

package com.piasecki.serviceImpl;

import com.piasecki.domain.Entrepreneurship;
import com.piasecki.service.TaxCalculationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TaxCalculatorService {
    private final TaxCalculatedStrategyFactory taxCalculatedStrategyFactory;

    @Autowired
    public TaxCalculatorService(TaxCalculatedStrategyFactory taxCalculatedStrategyFactory) {
        this.taxCalculatedStrategyFactory = taxCalculatedStrategyFactory;
    }

    public BigDecimal calculateTax(Entrepreneurship entrepreneurship) {
        TaxCalculationStrategy taxCalculationStrategy = taxCalculatedStrategyFactory.create(entrepreneurship);
        return taxCalculationStrategy.calculateIncomeTax();
    }

    }

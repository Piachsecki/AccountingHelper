package com.piasecki.serviceImpl;

import com.piasecki.domain.Entrepreneurship;
import com.piasecki.service.TaxCalculationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TaxCalculatorService {
    //tutaj powinien byc Security Utils i UserService uzyte zamiast w kontroloerze
    private final TaxCalculatedStrategyFactory taxCalculatedStrategyFactory;

    @Autowired
    public TaxCalculatorService(TaxCalculatedStrategyFactory taxCalculatedStrategyFactory) {
        this.taxCalculatedStrategyFactory = taxCalculatedStrategyFactory;
    }

    public BigDecimal calculateTax(Entrepreneurship entrepreneurship) {
        TaxCalculationStrategy taxCalculationStrategy = taxCalculatedStrategyFactory.create(entrepreneurship);
        return taxCalculationStrategy.calculateIncomeTax();
        //tutaj pozniej jak juz stworzymy te strategie - to nie musza jzu byc springowymi beanami - scopePrototype
        // wtedy innym podejsciem jest wziecie tej strategii jako single beana w postaci scopePrototype - getBean

    }

}

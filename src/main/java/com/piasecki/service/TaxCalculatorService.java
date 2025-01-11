package com.piasecki.service;

import com.piasecki.domain.Entrepreneurship;
import com.piasecki.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TaxCalculatorService {
    //tutaj powinien byc Security Utils i UserService uzyte zamiast w kontroloerze
    private final TaxCalculatedStrategyFactory taxCalculatedStrategyFactory;
    private final UserService userService;


    public BigDecimal calculateTax() {
        Entrepreneurship entrepreneurship = SecurityUtils.getCurrentUser(userService).getEntrepreneurship();
        TaxCalculationStrategy taxCalculationStrategy = taxCalculatedStrategyFactory.create(entrepreneurship);
        return taxCalculationStrategy.calculateIncomeTax();


        //tutaj pozniej jak juz stworzymy te strategie - to nie musza jzu byc springowymi beanami - scopePrototype
        // wtedy innym podejsciem jest wziecie tej strategii jako single beana w postaci scopePrototype - getBean

    }

}

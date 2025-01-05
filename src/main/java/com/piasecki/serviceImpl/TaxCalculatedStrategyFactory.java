package com.piasecki.serviceImpl;

import com.piasecki.domain.Entrepreneurship;
import com.piasecki.domain.TaxationMethod;
import com.piasecki.service.IncomeCalculator;
import com.piasecki.service.RevenueCalculator;
import com.piasecki.service.TaxCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class TaxCalculatedStrategyFactory {
    private final IncomeCalculator incomeCalculator;
    private final RevenueCalculator revenueCalculator;

    public TaxCalculationStrategy create(Entrepreneurship entrepreneurship) {
        TaxationMethod taxationMethod = entrepreneurship.getTaxationMethod();
        if (TaxationMethod.FLAT_TAX.equals(taxationMethod)) {
            return new FlatTaxCalculationStrategy(incomeCalculator);
        } else if (TaxationMethod.TAX_SCALE.equals(taxationMethod)) {
            return new ScaleTaxCalculationStrategy(incomeCalculator);
        } else {
            LumpSumTaxCalculationStrategy lumpSumTaxCalculationStrategy = new LumpSumTaxCalculationStrategy(revenueCalculator);
            lumpSumTaxCalculationStrategy.setBusinessActivity(entrepreneurship.getBusinessActivity());
            return lumpSumTaxCalculationStrategy;
        }
    }
}



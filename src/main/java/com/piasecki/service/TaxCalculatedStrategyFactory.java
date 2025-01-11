package com.piasecki.service;

import com.piasecki.domain.Entrepreneurship;
import com.piasecki.domain.TaxationMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaxCalculatedStrategyFactory {

    private final FlatTaxCalculationStrategy flatTaxCalculationStrategy;
    private final ScaleTaxCalculationStrategy scaleTaxCalculationStrategy;
    private final LumpSumTaxCalculationStrategy lumpSumTaxCalculationStrategy;

    public TaxCalculationStrategy create(Entrepreneurship entrepreneurship) {
        TaxationMethod taxationMethod = entrepreneurship.getTaxationMethod();
        if (TaxationMethod.FLAT_TAX.equals(taxationMethod)) {
            return flatTaxCalculationStrategy;

        } else if (TaxationMethod.TAX_SCALE.equals(taxationMethod)) {
            return scaleTaxCalculationStrategy;
        } else {
            lumpSumTaxCalculationStrategy.setBusinessActivity(entrepreneurship.getBusinessActivity());
            return lumpSumTaxCalculationStrategy;
        }
    }
}


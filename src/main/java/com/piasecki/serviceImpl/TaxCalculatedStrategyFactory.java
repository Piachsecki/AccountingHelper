package com.piasecki.serviceImpl;

import com.piasecki.domain.Entrepreneurship;
import com.piasecki.domain.TaxationMethod;
import com.piasecki.service.TaxCalculationStrategy;

import java.math.BigDecimal;

public class TaxCalculatedStrategyFactory {

    public static TaxCalculationStrategy create(Entrepreneurship entrepreneurship) {
        TaxationMethod taxationMethod = entrepreneurship.getTaxationMethod();
        if (TaxationMethod.FLAT_TAX.equals(taxationMethod)) {
            FlatTaxCalculationStrategy flatTaxCalculationStrategy = new FlatTaxCalculationStrategy();
            return flatTaxCalculationStrategy;
        } else if (TaxationMethod.TAX_SCALE.equals(taxationMethod)) {
            ScaleTaxCalculationStrategy scaleTaxCalculationStrategy = new ScaleTaxCalculationStrategy();
            return scaleTaxCalculationStrategy;

        }else {
            LumpSumTaxCalculationStrategy lumpSumTaxCalculationStrategy = new LumpSumTaxCalculationStrategy();
            return lumpSumTaxCalculationStrategy;
        }
    }
}



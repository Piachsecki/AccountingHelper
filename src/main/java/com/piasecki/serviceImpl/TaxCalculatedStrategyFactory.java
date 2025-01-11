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
    //tych 2 klas ma tu nie byc (w ich implementacjach zrob finale i RequiredArgsConstructor)
    //sprawdzic czy to ma wszystko byc beanami springowymi - ale raczej tak - raczej je zostaw
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

/*
  private final IncomeCalculator incomeCalculator;
    private final RevenueCalculator revenueCalculator;
    te 2 serwisy przy starcie aplikacji juz istnieja - bo spring sie odpala
 */


/*
Gdy zrobisz te rzeczy jako final i Required konstruktor zaminisz, to wtedy bedziesz mogl zrobic te strategie jako pola w tej klasie
i zwracac je odpowiednio od taxiationMethod
 */

/*
    W faktorce powinienem zamiast wstrzykiwac te 2 serwisy do faktorki, to powinienem to brac z Application Contextu
 */
package com.piasecki.serviceImpl;


import com.piasecki.domain.Entrepreneurship;
import com.piasecki.service.IncomeCalculator;
import com.piasecki.service.TaxCalculationStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

//TODO tutaj wzorzec projektowy strategii ale wlasnie jak ?
@Service
@Data
@AllArgsConstructor
public class ScaleTaxCalculationStrategy implements TaxCalculationStrategy {
    private IncomeCalculator incomeCalculator;



    @Override
    public BigDecimal calculateIncomeTax() {
        BigDecimal income = incomeCalculator.calculateIncome();
        if (income.compareTo(BigDecimal.valueOf(10000)) >= 0) {
            return income.multiply(BigDecimal.valueOf(0.32));
        }
        return income.multiply(BigDecimal.valueOf(0.17));
    }
}

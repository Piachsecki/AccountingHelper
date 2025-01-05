package com.piasecki.serviceImpl;


import com.piasecki.domain.Entrepreneurship;
import com.piasecki.service.IncomeCalculator;
import com.piasecki.service.TaxCalculationStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

//TODO tutaj wzorzec projektowy strategii ale wlasnie jak ?
@Service
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ScaleTaxCalculationStrategy implements TaxCalculationStrategy {
    private IncomeCalculator incomeCalculator;
    @Override
    public BigDecimal calculateIncomeTax() {
        return null;
    }
}

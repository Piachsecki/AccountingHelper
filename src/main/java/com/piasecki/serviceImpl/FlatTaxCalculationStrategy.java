package com.piasecki.serviceImpl;

import com.piasecki.domain.Entrepreneurship;
import com.piasecki.service.IncomeCalculator;
import com.piasecki.service.TaxCalculationStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
@Data
public class FlatTaxCalculationStrategy implements TaxCalculationStrategy {
    private IncomeCalculator incomeCalculator;
    @Override
    public BigDecimal calculateIncomeTax() {
        return BigDecimal.valueOf(0.19).multiply(incomeCalculator.calculateIncome());
    }
}

package com.piasecki.serviceImpl;

import com.piasecki.domain.Entrepreneurship;
import com.piasecki.service.IncomeCalculator;
import com.piasecki.service.TaxCalculationStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
@Data
@NoArgsConstructor
public class LumpSumTaxCalculationStrategy implements TaxCalculationStrategy {
    private IncomeCalculator incomeCalculator;
    private BigDecimal amount;

    @Override
    public BigDecimal calculateIncomeTax() {
        return null;
    }
}
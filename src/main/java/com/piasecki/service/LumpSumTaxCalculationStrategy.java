package com.piasecki.service;

import com.piasecki.domain.BusinessActivity;
import lombok.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Data
public class LumpSumTaxCalculationStrategy implements TaxCalculationStrategy {
    private final RevenueCalculator revenueCalculator;
    private BusinessActivity businessActivity;
    @Override
    public BigDecimal calculateIncomeTax() {
        BigDecimal revenue = revenueCalculator.calculateRevenue();

        return revenue.multiply(BigDecimal.valueOf(businessActivity.getPercentage()));
    }
}
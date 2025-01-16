package com.piasecki.service;

import com.piasecki.domain.BusinessActivity;
import lombok.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Data
public class LumpSumTaxCalculationStrategy implements TaxCalculationStrategy {
    private final RevenueCalculator revenueCalculator;
    private BusinessActivity businessActivity;
    @Override
    public BigDecimal calculateIncomeTax(LocalDate specifiedDate) {
        BigDecimal revenue = revenueCalculator.calculateRevenue(specifiedDate);
        if (revenue.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }
        return revenue.multiply(BigDecimal.valueOf(businessActivity.getPercentage()));
    }
}
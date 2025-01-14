package com.piasecki.service.unit;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.piasecki.domain.BusinessActivity;
import com.piasecki.service.LumpSumTaxCalculationStrategy;
import com.piasecki.service.RevenueCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

class LumpSumTaxCalculationStrategyTest {

    @Mock
    private RevenueCalculator revenueCalculator;

    @Mock
    private BusinessActivity businessActivity;

    @InjectMocks
    private LumpSumTaxCalculationStrategy lumpSumTaxCalculationStrategy;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void calculateIncomeTax_ShouldReturnCorrectTaxForPositiveRevenue() {
        // Arrange
        when(revenueCalculator.calculateRevenue()).thenReturn(BigDecimal.valueOf(50000));
        when(businessActivity.getPercentage()).thenReturn(0.15);
        lumpSumTaxCalculationStrategy.setBusinessActivity(businessActivity);

        // Act
        BigDecimal tax = lumpSumTaxCalculationStrategy.calculateIncomeTax();

        // Assert
        assertNotNull(tax);
        assertEquals(BigDecimal.valueOf(7500.00), tax);
        verify(revenueCalculator, times(1)).calculateRevenue();
        verify(businessActivity, times(1)).getPercentage();
    }

    @Test
    void calculateIncomeTax_ShouldReturnZeroForZeroRevenue() {
        // Arrange
        when(revenueCalculator.calculateRevenue()).thenReturn(BigDecimal.ZERO);
        when(businessActivity.getPercentage()).thenReturn(0.15);
        lumpSumTaxCalculationStrategy.setBusinessActivity(businessActivity);

        // Act
        BigDecimal tax = lumpSumTaxCalculationStrategy.calculateIncomeTax();

        // Assert
        assertNotNull(tax);
        assertEquals(BigDecimal.ZERO, tax);
        verify(revenueCalculator, times(1)).calculateRevenue();
        verify(businessActivity, never()).getPercentage();
    }

    @Test
    void calculateIncomeTax_ShouldReturnZeroForNegativeRevenue() {
        // Arrange
        when(revenueCalculator.calculateRevenue()).thenReturn(BigDecimal.valueOf(-20000));
        when(businessActivity.getPercentage()).thenReturn(0.15);
        lumpSumTaxCalculationStrategy.setBusinessActivity(businessActivity);

        // Act
        BigDecimal tax = lumpSumTaxCalculationStrategy.calculateIncomeTax();

        // Assert
        assertNotNull(tax);
        assertEquals(BigDecimal.ZERO, tax);
        verify(revenueCalculator, times(1)).calculateRevenue();
        verify(businessActivity, never()).getPercentage();
    }
}

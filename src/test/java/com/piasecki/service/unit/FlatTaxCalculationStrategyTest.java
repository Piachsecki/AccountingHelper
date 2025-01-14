package com.piasecki.service.unit;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.piasecki.service.FlatTaxCalculationStrategy;
import com.piasecki.service.IncomeCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

class FlatTaxCalculationStrategyTest {

    @Mock
    private IncomeCalculator incomeCalculator;

    @InjectMocks
    private FlatTaxCalculationStrategy flatTaxCalculationStrategy;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void calculateIncomeTax_ShouldReturnCorrectTaxForPositiveIncome() {
        // Arrange
        when(incomeCalculator.calculateIncome()).thenReturn(BigDecimal.valueOf(10000));

        // Act
        BigDecimal tax = flatTaxCalculationStrategy.calculateIncomeTax();

        // Assert
        assertNotNull(tax);
        assertEquals(BigDecimal.valueOf(1900.0), tax);
        verify(incomeCalculator, times(1)).calculateIncome();
    }

    @Test
    void calculateIncomeTax_ShouldReturnZeroForZeroIncome() {
        // Arrange
        when(incomeCalculator.calculateIncome()).thenReturn(BigDecimal.ZERO);

        // Act
        BigDecimal tax = flatTaxCalculationStrategy.calculateIncomeTax();

        // Assert
        assertNotNull(tax);
        assertEquals(BigDecimal.ZERO, tax);
        verify(incomeCalculator, times(1)).calculateIncome();
    }

    @Test
    void calculateIncomeTax_ShouldReturnZeroForNegativeIncome() {
        // Arrange
        when(incomeCalculator.calculateIncome()).thenReturn(BigDecimal.valueOf(-5000));

        // Act
        BigDecimal tax = flatTaxCalculationStrategy.calculateIncomeTax();

        // Assert
        assertNotNull(tax);
        assertEquals(BigDecimal.ZERO, tax);
        verify(incomeCalculator, times(1)).calculateIncome();
    }
}
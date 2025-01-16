//package com.piasecki.service.unit;
//
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//import com.piasecki.service.IncomeCalculator;
//import com.piasecki.service.ScaleTaxCalculationStrategy;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.math.BigDecimal;
//
//class ScaleTaxCalculationStrategyTest {
//
//    @Mock
//    private IncomeCalculator incomeCalculator;
//
//    @InjectMocks
//    private ScaleTaxCalculationStrategy scaleTaxCalculationStrategy;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void calculateIncomeTax_ShouldReturnZero_ForZeroIncome() {
//        // Arrange
//        when(incomeCalculator.calculateIncome()).thenReturn(BigDecimal.ZERO);
//
//        // Act
//        BigDecimal tax = scaleTaxCalculationStrategy.calculateIncomeTax();
//
//        // Assert
//        assertNotNull(tax);
//        assertEquals(BigDecimal.ZERO, tax);
//        verify(incomeCalculator, times(1)).calculateIncome();
//    }
//
//    @Test
//    void calculateIncomeTax_ShouldReturnZero_ForNegativeIncome() {
//        // Arrange
//        when(incomeCalculator.calculateIncome()).thenReturn(BigDecimal.valueOf(-5000));
//
//        // Act
//        BigDecimal tax = scaleTaxCalculationStrategy.calculateIncomeTax();
//
//        // Assert
//        assertNotNull(tax);
//        assertEquals(BigDecimal.ONE, tax);
//        verify(incomeCalculator, times(1)).calculateIncome();
//    }
//
//    @Test
//    void calculateIncomeTax_ShouldApply17PercentTax_ForIncomeBelowThreshold() {
//        // Arrange
//        when(incomeCalculator.calculateIncome()).thenReturn(BigDecimal.valueOf(8000));
//
//        // Act
//        BigDecimal tax = scaleTaxCalculationStrategy.calculateIncomeTax();
//
//        // Assert
//        assertNotNull(tax);
//        assertEquals(BigDecimal.valueOf(1360.00).setScale(2), tax);
//        verify(incomeCalculator, times(1)).calculateIncome();
//    }
//
//    @Test
//    void calculateIncomeTax_ShouldApply32PercentTax_ForIncomeAtThreshold() {
//        // Arrange
//        when(incomeCalculator.calculateIncome()).thenReturn(BigDecimal.valueOf(10000));
//
//        // Act
//        BigDecimal tax = scaleTaxCalculationStrategy.calculateIncomeTax();
//
//        // Assert
//        assertNotNull(tax);
//        assertEquals(BigDecimal.valueOf(3200.00).setScale(2), tax);
//        verify(incomeCalculator, times(1)).calculateIncome();
//    }
//
//    @Test
//    void calculateIncomeTax_ShouldApply32PercentTax_ForIncomeAboveThreshold() {
//        // Arrange
//        when(incomeCalculator.calculateIncome()).thenReturn(BigDecimal.valueOf(15000));
//
//        // Act
//        BigDecimal tax = scaleTaxCalculationStrategy.calculateIncomeTax();
//
//        // Assert
//        assertNotNull(tax);
//        assertEquals(BigDecimal.valueOf(4800.00).setScale(2), tax);
//        verify(incomeCalculator, times(1)).calculateIncome();
//    }
//
//    @Test
//    void calculateIncomeTax_ShouldHandleEdgeCaseIncome() {
//        // Arrange
//        when(incomeCalculator.calculateIncome()).thenReturn(BigDecimal.valueOf(9999.99));
//
//        // Act
//        BigDecimal tax = scaleTaxCalculationStrategy.calculateIncomeTax();
//
//        // Assert
//        assertNotNull(tax);
//        assertEquals(BigDecimal.valueOf(1699.9983).setScale(2, BigDecimal.ROUND_HALF_UP), tax);
//        verify(incomeCalculator, times(1)).calculateIncome();
//    }
//}

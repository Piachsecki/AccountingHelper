package com.piasecki.service.unit;

import com.piasecki.domain.Entrepreneurship;
import com.piasecki.domain.TaxationMethod;
import com.piasecki.domain.User;
import com.piasecki.service.*;
import com.piasecki.utils.SecurityUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
public class TaxCalculatorServiceTest {
    @Mock
    private TaxCalculatedStrategyFactory taxCalculatedStrategyFactory;

    @Mock
    private UserService userService;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @Mock
    private TaxCalculationStrategy taxCalculationStrategy;

    @InjectMocks
    private TaxCalculatorService taxCalculatorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void calculateTax_ShouldReturnCalculatedTax() {
        // Arrange
        User mockUser = new User();
        Entrepreneurship mockEntrepreneurship = new Entrepreneurship();
        mockEntrepreneurship.setTaxationMethod(TaxationMethod.FLAT_TAX);
        mockUser.setEntrepreneurship(mockEntrepreneurship);

        when(userService.findByUsername(anyString())).thenReturn(mockUser);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(mock(UserDetails.class));
        when(((UserDetails) authentication.getPrincipal()).getUsername()).thenReturn("testUser");
        SecurityContextHolder.setContext(securityContext);

        when(taxCalculatedStrategyFactory.create(mockEntrepreneurship)).thenReturn(taxCalculationStrategy);
        when(taxCalculationStrategy.calculateIncomeTax()).thenReturn(BigDecimal.valueOf(1000.00));

        // Act
        BigDecimal tax = taxCalculatorService.calculateTax();

        // Assert
        assertNotNull(tax);
        assertEquals(BigDecimal.valueOf(1000.00), tax);

        verify(userService, times(1)).findByUsername("testUser");
        verify(taxCalculatedStrategyFactory, times(1)).create(mockEntrepreneurship);
        verify(taxCalculationStrategy, times(1)).calculateIncomeTax();
    }




}

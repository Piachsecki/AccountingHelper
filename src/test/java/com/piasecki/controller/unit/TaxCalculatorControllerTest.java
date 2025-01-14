package com.piasecki.controller.unit;


import com.piasecki.controller.TaxCalculatorController;
import com.piasecki.service.TaxCalculatorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaxCalculatorControllerTest {

    @InjectMocks
    private TaxCalculatorController taxCalculatorController;

    @Mock
    private TaxCalculatorService taxCalculatorService;


    @Test
    public void shouldCalculatePositiveTax() {
        BigDecimal expectedRevenue = BigDecimal.valueOf(1000.00);
        when(taxCalculatorService.calculateTax()).thenReturn(expectedRevenue);

        ResponseEntity<BigDecimal> response = taxCalculatorController.calculateMyTax();

        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        Assertions.assertEquals(expectedRevenue, response.getBody());

    }




}

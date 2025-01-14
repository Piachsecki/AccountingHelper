package com.piasecki.controller.unit;


import com.piasecki.controller.IncomeController;
import com.piasecki.service.IncomeCalculator;
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
public class IncomeControllerTest {

    @InjectMocks
    private IncomeController incomeController;

//    @Mock
//    private RevenueCalculator revenueCalculator;
//
//    @Mock
//    private BusinessExpensesCalculator businessExpensesCalculator;

    @Mock
    private IncomeCalculator incomeCalculator;


    @Test
    public void shouldCalculatePositiveIncome() {
//        TaxationMethod taxationMethod = TaxationMethod.FLAT_TAX;
//        when(revenueCalculator.calculateRevenue()).thenReturn(new BigDecimal("100000.00"));
//        when(businessExpensesCalculator.calculateBusinessExpenses()).thenReturn(new BigDecimal("50000.00"));
//
        //given, when
        BigDecimal expectedIncome = new BigDecimal("100000.00");
        when(incomeCalculator.calculateIncome()).thenReturn(new BigDecimal("100000.00"));
        ResponseEntity<BigDecimal> response = incomeController.calculateIncome();


        //then
        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        Assertions.assertEquals(expectedIncome, response.getBody());
    }


    @Test
    public void shouldCalculateNegativeIncome() {
        //given, when
        BigDecimal expectedIncome = new BigDecimal("-100000.00");
        when(incomeCalculator.calculateIncome()).thenReturn(new BigDecimal("-100000.00"));
        ResponseEntity<BigDecimal> response = incomeController.calculateIncome();


        //then
        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        Assertions.assertEquals(expectedIncome, response.getBody());
    }



    @Test
    public void shouldCalculateZeroIncome() {
        //given, when
        BigDecimal expectedIncome = BigDecimal.ZERO;
        when(incomeCalculator.calculateIncome()).thenReturn(new BigDecimal("0"));
        ResponseEntity<BigDecimal> response = incomeController.calculateIncome();


        //then
        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        Assertions.assertEquals(expectedIncome, response.getBody());
    }

}

//package com.piasecki.controller.unit;
//
//
//import com.piasecki.controller.RevenueController;
//import com.piasecki.service.RevenueCalculator;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ResponseEntity;
//
//import java.math.BigDecimal;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.when;
//
//
//
///*
//To sa testy jednostkowe kontrollerow ?
// */
//@ExtendWith(MockitoExtension.class)
//public class RevenueControllerTest {
//
//    @InjectMocks
//    private RevenueController revenueController;
//
//    @Mock
//    private RevenueCalculator revenueCalculator;
//
//
//    @Test
//    public void shouldReturnProperRevenueInResponseEntity() {
//        // Arrange
//        BigDecimal expectedRevenue = BigDecimal.valueOf(1000.00);
//        when(revenueCalculator.calculateRevenue()).thenReturn(expectedRevenue);
//        // Act
//        ResponseEntity<BigDecimal> response = revenueController.calculateRevenue();
//
//        // Assert
//        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
//        Assertions.assertEquals(expectedRevenue, response.getBody());
//    }
//
//
//    @Test
//    public void shouldReturn0InResponseEntity() {
//        // Arrange
//        BigDecimal expectedRevenue = BigDecimal.valueOf(0);
//        when(revenueCalculator.calculateRevenue()).thenReturn(expectedRevenue);
//        // Act
//        ResponseEntity<BigDecimal> response = revenueController.calculateRevenue();
//
//        // Assert
//        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
//        Assertions.assertEquals(expectedRevenue, response.getBody());
//    }
//
//
//
//}

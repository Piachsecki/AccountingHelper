package com.piasecki.controller;

import com.piasecki.service.BusinessExpensesCalculator;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("/api/businessExpenses")
public class BusinessExpensesController {
    private BusinessExpensesCalculator businessExpensesCalculator;
    @GetMapping(value = "/calculate")
    public ResponseEntity<BigDecimal> calculateRevenue(
            @RequestParam(value = "month", required = false) Integer month,
            @RequestParam(value = "year", required = false) Integer year
    ) {
        if(Objects.isNull(month) || Objects.isNull(year)) {
            LocalDate currentDate = LocalDate.now();
            LocalDate specifiedDate = LocalDate.of(currentDate.getYear(), currentDate.getMonthValue(), 1);
            return ResponseEntity.ok(businessExpensesCalculator.calculateBusinessExpenses(specifiedDate));
        }
        Month specifiedMonth = Month.of(month);
        LocalDate specifiedDate = LocalDate.of(year, specifiedMonth, 1);
        BigDecimal businessExpenses = businessExpensesCalculator.calculateBusinessExpenses(specifiedDate);
        return ResponseEntity.ok(businessExpenses);
    }
}

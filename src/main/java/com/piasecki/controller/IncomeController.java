package com.piasecki.controller;

import com.piasecki.service.IncomeCalculator;
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
@RequestMapping("/api/income")
public class IncomeController  {
    private IncomeCalculator incomeCalculator;

    @GetMapping(value = "/calculate")
    public ResponseEntity<BigDecimal> calculateIncome(
            @RequestParam(value = "month", required = false) Integer month,
            @RequestParam(value = "year", required = false) Integer year
    ) {
        if(Objects.isNull(month) || Objects.isNull(year)) {
            LocalDate currentDate = LocalDate.now();
            LocalDate specifiedDate = LocalDate.of(currentDate.getYear(), currentDate.getMonthValue(), 1);
            return ResponseEntity.ok(incomeCalculator.calculateIncome(specifiedDate));
        }
        Month specifiedMonth = Month.of(month);
        LocalDate specifiedDate = LocalDate.of(year, specifiedMonth, 1);
        BigDecimal income = incomeCalculator.calculateIncome(specifiedDate);
        return ResponseEntity.ok(income);
    }
}

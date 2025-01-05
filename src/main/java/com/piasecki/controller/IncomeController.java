package com.piasecki.controller;

import com.piasecki.service.IncomeCalculator;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@AllArgsConstructor
@RequestMapping("/api/income")
public class IncomeController  {
    private IncomeCalculator incomeCalculator;

    @GetMapping(value = "/calculate")
    public ResponseEntity<BigDecimal> calculateIncome() {
        BigDecimal income = incomeCalculator.calculateIncome();
        return ResponseEntity.ok(income);
    }
}

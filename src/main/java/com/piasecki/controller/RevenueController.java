package com.piasecki.controller;


import com.piasecki.service.RevenueCalculator;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@AllArgsConstructor
@RequestMapping("/api/revenue")
public class RevenueController {
    private RevenueCalculator revenueCalculator;

    @GetMapping(value = "/generate")
    public ResponseEntity<BigDecimal> calculateIncome() {
        BigDecimal revenue = revenueCalculator.calculateRevenue();
        return ResponseEntity.ok(revenue);
    }
}

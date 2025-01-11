package com.piasecki.controller;


import com.piasecki.service.TaxCalculatorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/tax")
@AllArgsConstructor
public class TaxCalculatorController {
    private TaxCalculatorService taxCalculatorService;
    @GetMapping(value = "/calculate")
    public ResponseEntity<BigDecimal> calculateMyTax() {
        BigDecimal tax = taxCalculatorService.calculateTax();
        return ResponseEntity.ok(tax);
    }

}

package com.piasecki.controller;


import com.piasecki.service.VatCalculator;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@AllArgsConstructor
@RequestMapping("/api/vat")
public class VatCalculatorController {
    private VatCalculator vatCalculator;

    @GetMapping(value = "/calculate")
    public ResponseEntity<BigDecimal> calculateVat() {
        return ResponseEntity.ok(vatCalculator.calculateVat());
    }
}

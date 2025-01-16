package com.piasecki.controller;


import com.piasecki.service.VatCalculator;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("/api/vat")
public class VatCalculatorController {
    private VatCalculator vatCalculator;

    @GetMapping(value = "/calculate")
    public ResponseEntity<BigDecimal> calculateVat(
            @RequestParam(value = "month", required = false) Integer month,
            @RequestParam(value = "year", required = false) Integer year
    ) {
        if(Objects.isNull(month) || Objects.isNull(year)) {
            LocalDate currentDate = LocalDate.now();
            LocalDate specifiedDate = LocalDate.of(currentDate.getYear(), currentDate.getMonthValue(), 1);
            return ResponseEntity.ok(vatCalculator.calculateVat(specifiedDate));
        }
        Month specifiedMonth = Month.of(month);
        LocalDate specifiedDate = LocalDate.of(year, specifiedMonth, 1);
        return ResponseEntity.ok(vatCalculator.calculateVat(specifiedDate));
    }
}

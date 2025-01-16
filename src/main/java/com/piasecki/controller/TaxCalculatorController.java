package com.piasecki.controller;


import com.piasecki.service.TaxCalculatorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;

@RestController
@RequestMapping("/api/tax")
@AllArgsConstructor
public class TaxCalculatorController {
    private TaxCalculatorService taxCalculatorService;

    @GetMapping(value = "/calculate")
    public ResponseEntity<BigDecimal> calculateMyTax(
            @RequestParam(value = "month", required = false) Integer month,
            @RequestParam(value = "year", required = false) Integer year)
    {
        if(Objects.isNull(month) || Objects.isNull(year)) {
            LocalDate currentDate = LocalDate.now();
            LocalDate specifiedDate = LocalDate.of(currentDate.getYear(), currentDate.getMonthValue(), 1);
            return ResponseEntity.ok(taxCalculatorService.calculateTax(specifiedDate));
        }
        Month specifiedMonth = Month.of(month);
        LocalDate specifiedDate = LocalDate.of(year, specifiedMonth, 1);
        BigDecimal tax = taxCalculatorService.calculateTax(specifiedDate);
        return ResponseEntity.ok(tax);
    }

}

package com.piasecki.controller;


import com.piasecki.security.SecurityConfig;
import com.piasecki.service.TaxCalculationStrategy;
import com.piasecki.service.UserService;
import com.piasecki.serviceImpl.TaxCalculatorService;
import com.piasecki.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/tax")
@AllArgsConstructor
public class TaxCalculatorController {
    private TaxCalculatorService taxCalculatorService;
    private UserService userService;
    @GetMapping(value = "/calculate")
    public ResponseEntity<BigDecimal> calculateMyTax() {
        //TODO NIE WIEM GDZIE MAM WYCIAGNAC typ opodatkowania po ktorym bedziemy obliczac ten podatek
        BigDecimal tax = taxCalculatorService.calculateTax(SecurityUtils.getCurrentUser(userService).getEntrepreneurship());
        return ResponseEntity.ok(tax);
    }

}

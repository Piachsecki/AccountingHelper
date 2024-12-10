package com.piasecki.controller;

import com.piasecki.dto.InvoiceDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@RestController
@AllArgsConstructor
@RequestMapping("/api/report")
public class ReportController {
//    private R

    @GetMapping(value = "/generate")
    public ResponseEntity<BigDecimal> generateFinancialReport() {
//        taxCalculatorService.calculateIncomeTax()
        return ResponseEntity.ok(new BigDecimal(1));
    }
}

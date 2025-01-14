package com.piasecki.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptDTO {
    private LocalDate date;
    private BigDecimal amount;
    private String currency;
    private BigDecimal taxRate;
    private boolean isNip;
}

package com.piasecki.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;


@Builder
@Data
public class ReceiptDTO {
    private LocalDate date;
    private BigDecimal amount;
    private String currency;
    private BigDecimal taxRate;
    private boolean isNip;
}

package com.piasecki.dto;

import com.piasecki.domain.Price;
import com.piasecki.domain.User;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReceiptDTO {
    private LocalDate date;
    private BigDecimal amount;
    private String currency;
}

package com.piasecki.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;



@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkerDTO {
    private String surname;
    private String name;
    private BigDecimal salary;
}

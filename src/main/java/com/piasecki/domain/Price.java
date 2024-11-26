package com.piasecki.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Price {
    private String currency;
    private BigDecimal amount;
}

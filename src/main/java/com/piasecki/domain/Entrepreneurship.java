package com.piasecki.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Entrepreneurship {
    private TaxationMethod taxationMethod;
    private BusinessActivity businessActivity;
    private boolean vat;
}

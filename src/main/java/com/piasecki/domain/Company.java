package com.piasecki.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.pl.NIP;

import javax.validation.constraints.Pattern;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;


    /*
    Niestety nie moze zostac uzyte, bo OCR moze nie znalezc danego NIPU ze zdjecia
     */
//    @NIP(message = "Invalid NIP number. Please provide a valid Polish VAT identification number!")
//    @Column(unique = true)
    private String NIP;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
}

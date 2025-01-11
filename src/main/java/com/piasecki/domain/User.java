package com.piasecki.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.pl.NIP;

import javax.validation.constraints.Pattern;
import java.util.List;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Include
    private String username;

    private String password;
    private String email;

    private String role;

    @Embedded
    private Entrepreneurship entrepreneurship;


    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Invoice> invoices;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Receipt> receipts;

    @NIP(message = "Invalid NIP number. Please provide a valid Polish VAT identification number!")
    @Column(unique = true)
    @ToString.Include
    private String NIP;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = true)
    private Address address;
}

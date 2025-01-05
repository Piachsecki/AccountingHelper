package com.piasecki.domain;

import jakarta.persistence.*;
import lombok.*;

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

//    private BusinessActivity businessActivity;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Invoice> invoices;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Receipt> receipts;

    //    @Validate()
    @Pattern(regexp = "\\b\\d{10}\\b|\\b\\d{3}-\\d{2}-\\d{2}-\\d{3}\\b")
    @ToString.Include
    private String NIP;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = true)
    private Address address;
}

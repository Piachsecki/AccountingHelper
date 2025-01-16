package com.piasecki.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "Username must not be null")
    private String username;

    @NotNull(message = "Email must not be null")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Password must not be null")
    private String password;

    private String role;

    @Embedded
    private Entrepreneurship entrepreneurship;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Invoice> invoices;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Receipt> receipts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Worker> workers;



    /*
    Dlaczego nie dziala z  nullable = false, a jak damy @NotNull to nagle validator dziala
     */
    @NIP(message = "Invalid NIP number. Please provide a valid Polish VAT identification number!")
    @NotNull(message = "NIP must not be null")
    @Column(unique = true, nullable = false)
//    @ToString.Include
    private String NIP;




    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = true)
    private Address address;
}

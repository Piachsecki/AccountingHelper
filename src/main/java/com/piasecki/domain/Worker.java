package com.piasecki.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String surname;

    @Column(unique = true, nullable = false)
    @NonNull
    private String bankAccountNumber;

    @Column(nullable = false)
    @NonNull
    private BigDecimal salary;

    @ManyToOne
    @JoinColumn(name = "user_id") // "user_id" będzie kolumną z kluczem obcym
    private User user;



    @ManyToMany
    @JoinTable(
            name = "worker_invoice", // Name of the join table
            joinColumns = @JoinColumn(name = "worker_id"), // Worker side join column
            inverseJoinColumns = @JoinColumn(name = "invoice_id") // Invoice side join column
    )
    private List<Invoice> invoices;


}

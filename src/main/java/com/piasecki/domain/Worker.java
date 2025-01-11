package com.piasecki.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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


    private String name;

    private String surname;

    @Column(unique = true, nullable = false)
    private String bankAccountNumber;

    @Column(nullable = false)
    private BigDecimal salary;


    @ManyToMany
    @JoinTable(
            name = "worker_invoice", // Name of the join table
            joinColumns = @JoinColumn(name = "worker_id"), // Worker side join column
            inverseJoinColumns = @JoinColumn(name = "invoice_id") // Invoice side join column
    )
    private List<Invoice> invoices;


}

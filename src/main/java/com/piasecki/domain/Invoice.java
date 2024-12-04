package com.piasecki.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String invoiceNumber;
    @Enumerated(EnumType.STRING)
    private InvoiceType invoiceType;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private BigDecimal taxRate;


    @OneToOne(cascade = CascadeType.ALL)
    private Company company;


    @Embedded
    private Price price;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany(mappedBy = "invoices")
    private List<Worker> workers;

}

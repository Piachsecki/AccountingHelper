package com.piasecki.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.simpleframework.xml.core.Validate;


import javax.annotation.Nullable;
import javax.validation.constraints.Pattern;
import java.util.List;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Invoice> invoices;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Receipt> receipts;

    //    @Validate()
    @Pattern(regexp = "\\b\\d{10}\\b|\\b\\d{3}-\\d{2}-\\d{2}-\\d{3}\\b")
    private String NIP;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = true)
    private Address address;
}

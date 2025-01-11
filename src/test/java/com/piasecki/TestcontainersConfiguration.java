package com.piasecki;

import com.piasecki.domain.Invoice;
import com.piasecki.domain.InvoiceType;
import com.piasecki.domain.Price;
import com.piasecki.domain.User;
import com.piasecki.service.InvoiceService;
import com.piasecki.service.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Testcontainers
@SpringBootTest
@Transactional
public class TestcontainersConfiguration {

    @Container
    static MySQLContainer mySQLContainer = new MySQLContainer<>(DockerImageName.parse("mysql:latest"));

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private UserService userService;

    @BeforeEach
    void contextLoads() {
        Assertions.assertNotNull(invoiceService);
        Assertions.assertNotNull(userService);
    }


    @Test
    public void first() {

        //zamiast obiektu podac jsona i go zserializowac jacksona / object mapperem

        // adnotacje pattern i validate mozliwe, ze dziala w trakcie serializacji/deserialziacji obiektu
        //moze ona dzialac w innych momentach
        // to ztestowac na poziomie endpointow

        //w

        User user = User.builder()
                .username("Kacper")
                .NIP("a1234567890")
                .build();

        userService.addUser(user);

        Invoice invoice = Invoice.builder()
                .price(new Price("PLN", BigDecimal.valueOf(2000.0)))
                .user(user)
                .issueDate(LocalDate.of(2024, 12, 10))
                .dueDate(LocalDate.of(2025, 2, 10))
                .invoiceNumber("asdasda")
                .invoiceType(InvoiceType.OUTGOING_INVOICE)
                .build();

        invoiceService.addInvoice(invoice);

        int size = invoiceService.getAllCostInvoices().size();
        Assertions.assertEquals(1, size);


    }
}
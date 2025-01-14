package com.piasecki.database;

import com.piasecki.domain.*;
import com.piasecki.service.InvoiceService;
import com.piasecki.service.UserService;
import com.piasecki.utils.SecurityUtils;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testcontainers.shaded.org.bouncycastle.oer.its.ieee1609dot2.EndEntityType.app;

@Testcontainers
@SpringBootTest
@Transactional
public class DatabaseTests {
    @MockBean
    private SecurityUtils securityUtils;

    @Container
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>(DockerImageName.parse("mysql:latest"));

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private UserService userService;

    @BeforeEach
    void setup() {
        assertNotNull(invoiceService);
        assertNotNull(userService);
    }


    @Test
    public void testAddUserWithValidData() {
        User user = User.builder()
                .username("ValidUser")
                .email("valid@example.com") // Provide a valid email
                .password("securePassword123") // Provide a non-null password
                .NIP("1234567890")
                .build();

        userService.addUser(user);
    }


    @Test
    public void testAddUserAndInvoice() {
        User user = User.builder()
                .username("ValidUser")
                .email("valid@example.com") // Provide a valid email
                .password("securePassword123") // Provide a non-null password
                .NIP("1234567890")
                .build();

        when(SecurityUtils.getCurrentUser(userService)).thenReturn(user);

        userService.addUser(user);

        Invoice invoice = Invoice.builder()
                .price(new Price("USD", BigDecimal.valueOf(1500.00)))
                .user(user)
                .issueDate(LocalDate.of(2024, 1, 15))
                .dueDate(LocalDate.of(2024, 2, 15))
                .invoiceNumber("INV123")
                .invoiceType(InvoiceType.INCOME_INVOICE)
                .build();
        invoiceService.addInvoice(invoice);

        List<Invoice> invoices = invoiceService.getAllInvoices();
        assertEquals(1, invoices.size());
        assertEquals("INV123", invoices.get(0).getInvoiceNumber());
    }

    @Test
    public void testFindUserByUsername() {
        User user = User.builder()
                .username("SearchUser")
                .NIP("0987654321")
                .build();
        userService.addUser(user);

        User foundUser = userService.findByUsername("SearchUser");
        assertNotNull(foundUser);
        assertEquals("SearchUser", foundUser.getUsername());
    }

    @Test
    public void testDeleteUserAndCascade() {
        User user = User.builder()
                .username("CascadeUser")
                .NIP("5678901234")
                .build();
        userService.addUser(user);

        Invoice invoice = Invoice.builder()
                .price(new Price("EUR", BigDecimal.valueOf(2000.00)))
                .user(user)
                .issueDate(LocalDate.now())
                .dueDate(LocalDate.now().plusDays(30))
                .invoiceNumber("CASC123")
                .invoiceType(InvoiceType.OUTGOING_INVOICE)
                .build();
        invoiceService.addInvoice(invoice);

        userService.deleteUserById(user.getId());

        List<Invoice> invoices = invoiceService.getAllInvoices();
        assertTrue(invoices.isEmpty(), "Cascading delete failed: Invoices were not removed.");
    }

    @Test
    public void testCascadePersistAddress() {
        Address address = Address.builder()
                .street("Main Street")
                .city("Cityville")
                .postalCode("12345")
                .build();

        User user = User.builder()
                .username("AddressUser")
                .NIP("1233211234")
                .address(address)
                .build();
        userService.addUser(user);

        User savedUser = userService.findByUsername("AddressUser");
        assertNotNull(savedUser.getAddress());
        assertEquals("Main Street", savedUser.getAddress().getStreet());
    }
}

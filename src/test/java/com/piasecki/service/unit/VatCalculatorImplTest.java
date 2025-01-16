//package com.piasecki.service.unit;
//
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//import com.piasecki.domain.*;
//import com.piasecki.service.*;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.math.BigDecimal;
//import java.util.Arrays;
//import java.util.Collections;
//
//class VatCalculatorImplTest {
//
//    @Mock
//    private InvoiceService invoiceService;
//
//    @Mock
//    private UserService userService;
//
//    @Mock
//    private ReceiptService receiptService;
//
//    @InjectMocks
//    private VatCalculatorImpl vatCalculator;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @AfterEach
//    void tearDown() {
//        SecurityContextHolder.clearContext();
//    }
//
//    @Test
//    void calculateVat_ShouldThrowException_IfUserIsNotVatPayer() {
//        // Arrange
//        User user = mock(User.class);
//        Entrepreneurship entrepreneurship = mock(Entrepreneurship.class);
//        when(user.getEntrepreneurship()).thenReturn(entrepreneurship);
//        when(entrepreneurship.isVat()).thenReturn(false);
//
//        mockAuthentication(user);
//
//        // Act & Assert
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> vatCalculator.calculateVat(null));
//        assertEquals("You are not a VAT payer!", exception.getMessage());
//    }
//
//    @Test
//    void calculateVat_ShouldReturnCorrectVat_WhenUserIsVatPayer() {
//        // Arrange
//        User user = mock(User.class);
//        Entrepreneurship entrepreneurship = mock(Entrepreneurship.class);
//        when(user.getEntrepreneurship()).thenReturn(entrepreneurship);
//        when(entrepreneurship.isVat()).thenReturn(true);
//
//        mockAuthentication(user);
//
//        Invoice incomeInvoice1 = createIncomingInvoice(BigDecimal.valueOf(1000), BigDecimal.valueOf(0.23));
//        Invoice incomeInvoice2 = createIncomingInvoice(BigDecimal.valueOf(2000), BigDecimal.valueOf(0.23));
//        when(invoiceService.getAllIncomeInvoices()).thenReturn(Arrays.asList(incomeInvoice1, incomeInvoice2));
//
//        Invoice costInvoice1 = createOutgoingInvoice(BigDecimal.valueOf(500), BigDecimal.valueOf(0.23));
//        Invoice costInvoice2 = createOutgoingInvoice(BigDecimal.valueOf(300), BigDecimal.valueOf(0.23));
//        when(invoiceService.getAllCostInvoices()).thenReturn(Arrays.asList(costInvoice1, costInvoice2));
//
//        Receipt receipt1 = createNIPReceipts(BigDecimal.valueOf(150), BigDecimal.valueOf(0.23), "PLN");
//        Receipt receipt2 = createNIPReceipts(BigDecimal.valueOf(80), BigDecimal.valueOf(0.23), "PLN");
//        when(receiptService.getAllReceipts()).thenReturn(Arrays.asList(receipt1, receipt2));
//
//        // Act
//        BigDecimal vat = vatCalculator.calculateVat();
//
//        // Assert
//        assertNotNull(vat);
//        assertEquals(BigDecimal.valueOf(-506.00).setScale(2), vat);
//    }
//
//    @Test
//    void calculateVat_ShouldHandleNullValuesInInvoices() {
//        // Arrange
//        User user = mock(User.class);
//        Entrepreneurship entrepreneurship = mock(Entrepreneurship.class);
//        when(user.getEntrepreneurship()).thenReturn(entrepreneurship);
//        when(entrepreneurship.isVat()).thenReturn(true);
//
//        mockAuthentication(user);
//
//        Invoice costInvoice = createOutgoingInvoice(null, null);
//        when(invoiceService.getAllCostInvoices()).thenReturn(Collections.singletonList(costInvoice));
//        when(invoiceService.getAllIncomeInvoices()).thenReturn(Collections.emptyList());
//        when(receiptService.getAllReceipts()).thenReturn(Collections.emptyList());
//
//        // Act
//        BigDecimal vat = vatCalculator.calculateVat();
//
//        // Assert
//        assertNotNull(vat);
//        assertEquals(BigDecimal.ZERO, vat);
//    }
//
//    private Invoice createOutgoingInvoice(BigDecimal amount, BigDecimal taxRate) {
//        Invoice invoice = mock(Invoice.class);
//        Price price = mock(Price.class);
//        when(price.getAmount()).thenReturn(amount);
//        when(invoice.getPrice()).thenReturn(price);
//        when(invoice.getTaxRate()).thenReturn(taxRate);
//        when(invoice.getInvoiceType()).thenReturn(InvoiceType.OUTGOING_INVOICE);
//        return invoice;
//    }
//
//
//    private Invoice createIncomingInvoice(BigDecimal amount, BigDecimal taxRate) {
//        Invoice invoice = mock(Invoice.class);
//        Price price = mock(Price.class);
//        when(price.getAmount()).thenReturn(amount);
//        when(invoice.getPrice()).thenReturn(price);
//        when(invoice.getTaxRate()).thenReturn(taxRate);
//        when(invoice.getInvoiceType()).thenReturn(InvoiceType.INCOME_INVOICE);
//        return invoice;
//    }
//
//    private Receipt createNIPReceipts(BigDecimal amount, BigDecimal taxRate, String currency) {
//        Receipt receipt = mock(Receipt.class);
//        Price price = mock(Price.class);
//        when(price.getAmount()).thenReturn(amount);
//        when(price.getCurrency()).thenReturn(currency);
//        when(receipt.getPrice()).thenReturn(price);
//        when(receipt.getTaxRate()).thenReturn(taxRate);
//        when(receipt.isNip()).thenReturn(true);
//        return receipt;
//    }
//
//    private void mockAuthentication(User user) {
//        Authentication authentication = mock(Authentication.class);
//        UserDetails userDetails = mock(UserDetails.class);
//        when(userDetails.getUsername()).thenReturn("testUser");
//        when(authentication.getPrincipal()).thenReturn(userDetails);
//
//        SecurityContext securityContext = mock(SecurityContext.class);
//        when(securityContext.getAuthentication()).thenReturn(authentication);
//        SecurityContextHolder.setContext(securityContext);
//
//        when(userService.findByUsername("testUser")).thenReturn(user);
//    }
//}

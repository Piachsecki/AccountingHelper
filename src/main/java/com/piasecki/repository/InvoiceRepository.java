package com.piasecki.repository;

import com.piasecki.domain.Invoice;
import com.piasecki.domain.InvoiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findAllByUserId(Long customerId);
    List<Invoice> findAllByUserIdAndInvoiceType(Long user_id, InvoiceType invoiceType);
    Optional<Invoice> findInvoiceByInvoiceNumber(String invoiceNumber);
    List<Invoice> findInvoicesByIssueDateBefore(LocalDate issueDate);

    List<Invoice> findAllByUserIdAndInvoiceTypeAndIssueDateBetween(Long id, InvoiceType invoiceType, LocalDate issueDate, LocalDate endOfMonth);
}

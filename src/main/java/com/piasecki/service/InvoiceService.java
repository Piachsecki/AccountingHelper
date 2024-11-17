package com.piasecki.service;

import com.piasecki.domain.Invoice;
import org.springframework.stereotype.Service;

import java.util.List;

public interface InvoiceService {
    Invoice getInvoice(long id);
    List<Invoice> getInvoicesByCustomerId(long id);
    void addInvoice(Invoice invoice);
    void updateInvoice(long id, Invoice invoice);

}

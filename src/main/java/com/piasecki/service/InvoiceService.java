package com.piasecki.service;

import com.piasecki.domain.Invoice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InvoiceService {
    Invoice getInvoice(long id);
    List<Invoice> getInvoicesByCustomerId(long id);
    Invoice addInvoice(Invoice invoice);
    void deleteInvoice(long id);
    void updateInvoice(long id, Invoice invoice);

}

package com.piasecki.serviceImpl;

import com.piasecki.domain.Invoice;
import com.piasecki.repository.InvoiceRepository;
import com.piasecki.service.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public Invoice getInvoice(long id) {
        return invoiceRepository.findById(id).get();

    }

    @Override
    public List<Invoice> getInvoicesByCustomerId(long id) {
        return invoiceRepository.findAllByUserId(id);
    }

    @Override
    public void addInvoice(Invoice invoice) {
        invoiceRepository.save(invoice);
    }

    @Override
    public void updateInvoice(long id, Invoice invoice) {

    }
}

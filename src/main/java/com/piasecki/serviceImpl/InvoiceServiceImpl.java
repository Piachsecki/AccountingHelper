package com.piasecki.serviceImpl;

import com.piasecki.domain.Invoice;
import com.piasecki.repository.InvoiceRepository;
import com.piasecki.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Invoice addInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    public void deleteInvoice(long id) {
        invoiceRepository.deleteById(id);
    }

    @Override
    public void updateInvoice(long id, Invoice invoice) {

    }
}

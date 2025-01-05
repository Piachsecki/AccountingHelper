package com.piasecki.serviceImpl;

import com.piasecki.domain.Invoice;
import com.piasecki.domain.InvoiceType;
import com.piasecki.domain.User;
import com.piasecki.repository.InvoiceRepository;
import com.piasecki.service.InvoiceService;
import com.piasecki.service.UserService;
import com.piasecki.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    private InvoiceRepository invoiceRepository;
    private UserService userService;

    @Override
    public Invoice getInvoice(long id) {
        return invoiceRepository.findById(id).get();
    }

    @Override
    public List<Invoice> getAllInvoices() {
        User currentUser = SecurityUtils.getCurrentUser(userService);
        return invoiceRepository.findAllByUserId(currentUser.getId());
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

    @Override
    public List<Invoice> getAllCostInvoices() {
        User currentUser = SecurityUtils.getCurrentUser(userService);
        return invoiceRepository.findAllByUserIdAndInvoiceType(currentUser.getId(), InvoiceType.OUTGOING_INVOICE);
    }

    @Override
    public List<Invoice> getAllIncomeInvoices() {
        User currentUser = SecurityUtils.getCurrentUser(userService);
        return invoiceRepository.findAllByUserIdAndInvoiceType(currentUser.getId(), InvoiceType.INCOME_INVOICE);
    }
}

package com.piasecki.mapper;


import com.piasecki.domain.Invoice;
import com.piasecki.dto.InvoiceDTO;


public interface InvoiceMapper {
    Invoice mapInvoiceDTOtoInvoiceEntity(InvoiceDTO invoiceDTO);
}

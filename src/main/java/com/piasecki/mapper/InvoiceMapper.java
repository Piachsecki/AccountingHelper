package com.piasecki.mapper;


import com.piasecki.domain.Invoice;
import com.piasecki.dto.InvoiceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;


public interface InvoiceMapper {
    Invoice mapInvoiceDTOtoInvoiceEntity(InvoiceDTO invoiceDTO);
}

package com.piasecki.mapperImpl;

import com.piasecki.domain.Address;
import com.piasecki.domain.Company;
import com.piasecki.domain.Invoice;
import com.piasecki.domain.Price;
import com.piasecki.dto.InvoiceDTO;
import com.piasecki.mapper.InvoiceMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class InvoiceMapperImpl implements InvoiceMapper {
    @Override
    public Invoice mapInvoiceDTOtoInvoiceEntity(InvoiceDTO invoiceDTO) {
        return Invoice.builder()
                .invoiceType(invoiceDTO.getInvoiceType())
                .invoiceNumber(invoiceDTO.getInvoiceNumber())
                .price(new Price(
                        invoiceDTO.getCurrency(),
                        invoiceDTO.getAmount()
                ))
                .issueDate(invoiceDTO.getIssueDate())
                .dueDate(invoiceDTO.getDueDate())
                .company(Company.builder()
                        .address(addressParser(invoiceDTO))
                        .NIP(invoiceDTO.getCompanyNIP())
                        .companyName(invoiceDTO.getCompanyName())
                        .build())
                .build();
    }

    private Address addressParser(InvoiceDTO invoiceDTO) {
        System.out.println(invoiceDTO.getCompanyAddress());
        if (Objects.nonNull(invoiceDTO.getCompanyAddress())) {

            //pusta tablica stringow zwrocic
            String[] splittedAddress = invoiceDTO.getCompanyAddress().split(" ");
            for (String ad : splittedAddress) {

            }
        }
        return null;
    }

}

package com.piasecki.mapperImpl;

import com.piasecki.domain.Invoice;
import com.piasecki.domain.Price;
import com.piasecki.dto.CompanyDTO;
import com.piasecki.dto.InvoiceDTO;
import com.piasecki.mapper.CompanyMapper;
import com.piasecki.mapper.InvoiceMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InvoiceMapperImpl implements InvoiceMapper {
    private CompanyMapper companyMapper;

    @Override
    public Invoice mapInvoiceDTOtoInvoiceEntity(InvoiceDTO invoiceDTO) {
        CompanyDTO companyDTO = invoiceDTO.getCompanyDTO();

        return Invoice.builder()
                .invoiceType(invoiceDTO.getInvoiceType())
                .invoiceNumber(invoiceDTO.getInvoiceNumber())
                .price(new Price(
                        invoiceDTO.getCurrency(),
                        invoiceDTO.getAmount()
                ))
                .issueDate(invoiceDTO.getIssueDate())
                .dueDate(invoiceDTO.getDueDate())
                .company(companyMapper.mapCompanyDTOtoCompanyEntity(companyDTO))
                .build();
    }


}

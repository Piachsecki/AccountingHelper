package com.piasecki.mapper;


import com.piasecki.domain.Invoice;
import com.piasecki.domain.Receipt;
import com.piasecki.dto.InvoiceDTO;
import com.piasecki.dto.ReceiptDTO;

public interface ReceiptMapper {
    Receipt mapReceiptDTOtoReceiptEntity(ReceiptDTO receiptDTO);

}

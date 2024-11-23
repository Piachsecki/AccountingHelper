package com.piasecki.service;

import com.piasecki.domain.Receipt;
import com.piasecki.dto.InvoiceDTO;
import com.piasecki.dto.ReceiptDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileService {
    InvoiceDTO uploadInvoiceFile(MultipartFile file);

    ReceiptDTO uploadReceiptFile(MultipartFile file);
}

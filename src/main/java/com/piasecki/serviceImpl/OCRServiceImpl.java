package com.piasecki.serviceImpl;

import com.piasecki.dto.InvoiceDTO;
import com.piasecki.dto.ReceiptDTO;
import com.piasecki.service.OCRService;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Service
public class OCRServiceImpl implements OCRService {
    @Override
    public InvoiceDTO ocrInvoice(Path path) {
        return null;
    }

    @Override
    public ReceiptDTO ocrReceipt(Path path) {
        return null;
    }
}

package com.piasecki.service;

import com.piasecki.dto.InvoiceDTO;
import com.piasecki.dto.ReceiptDTO;

import java.nio.file.Path;

public interface OCRService {
    InvoiceDTO ocrInvoice(Path path);
    ReceiptDTO ocrReceipt(Path path);
}


/*
SERWIS I JEGO IMPLEMENTACJA MUSI BYC W TYM SAMYM PAKIECIE!!!!!
 */
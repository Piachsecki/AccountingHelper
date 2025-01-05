package com.piasecki.service;

import com.piasecki.domain.Receipt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReceiptService {
    Receipt getReceipt(Long id);
    Receipt addReceipt(Receipt receipt);
    void deleteReceipt(Long id);
    List<Receipt> getAllReceipts();
}

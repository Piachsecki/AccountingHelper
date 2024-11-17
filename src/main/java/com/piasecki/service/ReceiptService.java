package com.piasecki.service;

import com.piasecki.domain.Receipt;
import org.springframework.stereotype.Service;

@Service
public interface ReceiptService {
    Receipt getReceipt(Long id);
    Receipt saveReceipt(Receipt receipt);
    void deleteReceipt(Long id);
}

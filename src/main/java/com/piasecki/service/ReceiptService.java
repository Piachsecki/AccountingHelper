package com.piasecki.service;

import com.piasecki.domain.Receipt;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Service
public interface ReceiptService {
    Receipt getReceipt(Long id);
    Receipt addReceipt(Receipt receipt);
    void deleteReceipt(Long id);
    List<Receipt> getAllReceipts();
    List<Receipt> getAllReceiptsByDate(LocalDate date);

}


/*
SERWIS I JEGO IMPLEMENTACJA MUSI BYC W TYM SAMYM PAKIECIE!!!!!
 */
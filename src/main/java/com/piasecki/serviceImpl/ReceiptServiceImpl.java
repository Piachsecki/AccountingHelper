package com.piasecki.serviceImpl;

import com.piasecki.domain.Receipt;
import com.piasecki.repository.ReceiptRepository;
import com.piasecki.service.ReceiptService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class ReceiptServiceImpl implements ReceiptService {
    private ReceiptRepository receiptRepository;

    @Override
    public Receipt getReceipt(Long id) {
        return null;
    }

    @Override
    public Receipt addReceipt(Receipt receipt) {
        return receiptRepository.save(receipt);
    }

    @Override
    public void deleteReceipt(Long id) {
        receiptRepository.deleteById(id);
    }
}

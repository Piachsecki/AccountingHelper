package com.piasecki.service;

import com.piasecki.domain.Receipt;
import com.piasecki.domain.User;
import com.piasecki.repository.ReceiptRepository;
import com.piasecki.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class ReceiptServiceImpl implements ReceiptService {
    private final ReceiptRepository receiptRepository;
    private final UserService userService;

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

    @Override
    public List<Receipt> getAllReceipts() {
        User currentUser = SecurityUtils.getCurrentUser(userService);
        return receiptRepository.findAllByUserId(currentUser.getId());
    }
}

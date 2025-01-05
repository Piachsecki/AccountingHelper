package com.piasecki.serviceImpl;

import com.piasecki.domain.Receipt;
import com.piasecki.domain.User;
import com.piasecki.repository.ReceiptRepository;
import com.piasecki.service.ReceiptService;
import com.piasecki.service.UserService;
import com.piasecki.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@AllArgsConstructor
@Service
public class ReceiptServiceImpl implements ReceiptService {
    private ReceiptRepository receiptRepository;
    private UserService userService;

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

package com.piasecki.mapper;

import com.piasecki.domain.Price;
import com.piasecki.domain.Receipt;
import com.piasecki.dto.ReceiptDTO;
import org.springframework.stereotype.Service;

@Service
public class ReceiptMapperImpl implements ReceiptMapper {
    @Override
    public Receipt mapReceiptDTOtoReceiptEntity(ReceiptDTO receiptDTO) {
        return Receipt.builder()
                .taxRate(receiptDTO.getTaxRate())
                .date(receiptDTO.getDate())
                .isNip(receiptDTO.isNip())
                .price(new Price(
                        receiptDTO.getCurrency(),
                        receiptDTO.getAmount()
                ))
                .build();
    }
}

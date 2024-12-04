package com.piasecki.mapper;


import com.piasecki.domain.Receipt;
import com.piasecki.dto.ReceiptDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;


//@Service
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReceiptMapper {
    ReceiptMapper INSTANCE = Mappers.getMapper(ReceiptMapper.class);

    @Mapping(source = "price.amount", target = "amount")
    @Mapping(source = "price.currency", target = "currency")
    ReceiptDTO toDto(Receipt receipt);

    @Mapping(source = "amount", target = "price.amount")
    @Mapping(source = "currency", target = "price.currency")
    Receipt toEntity(ReceiptDTO receiptDTO);
}

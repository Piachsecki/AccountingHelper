package com.piasecki.dto;

import com.piasecki.domain.InvoiceType;
import com.piasecki.domain.Price;
import com.piasecki.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class InvoiceDTO {

    private String invoiceNumber;
    private InvoiceType invoiceType;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private BigDecimal amount;
    private String currency;



    //nie powinno byc uzytkownika tutaj tylko wziete ze spring security jakos
    //minio - zapisywanie plikow usera w S3. Zdockerowyzowac to i przetrzymywac te invoice w minio




    //zapisuje caly string z paragonu do bazy danych i zwracamy to do Usera: - on to widzi
    //i uzytkownik albo je akceptuje albo recnzie poprawia

    //to by wygladalo tak jak ponizej:

    // biore plik, najpierw do ocr, zczytujemy dane, i potem 2 operacje: 1. zapisanie do bazy danych (encji) 2.wrzucanie samego pliku do minio
    // i dane odczytane z ocr zwracam do usera

    //uploadFile ...   ---> do minio od razu???
    //update






}

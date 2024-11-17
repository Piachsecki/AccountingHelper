package com.piasecki.controller;


import com.piasecki.domain.Receipt;
import com.piasecki.domain.User;
import com.piasecki.service.ReceiptService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/receipt")
public class ReceiptController {
    private final ReceiptService receiptService;

//    public ReceiptController(ReceiptService receiptService) {
//        this.receiptService = receiptService;
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Receipt createReceipt(@RequestBody Receipt receipt) {
        return receiptService.saveReceipt(receipt);
    }

}

package com.piasecki.controller;


import com.piasecki.domain.Receipt;
import com.piasecki.dto.ReceiptDTO;
import com.piasecki.service.FileService;
import com.piasecki.service.ReceiptService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping("/api/receipt")
public class ReceiptController {
    private final ReceiptService receiptService;
    private final FileService fileService;

    @PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReceiptDTO> uploadFile(@RequestPart(value = "file") MultipartFile file) {
        ReceiptDTO receiptDTO = fileService.uploadReceiptFile(file);
        return ResponseEntity.ok(receiptDTO);
    }
//    public ReceiptController(ReceiptService receiptService) {
//        this.receiptService = receiptService;
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Receipt createReceipt(@RequestBody Receipt receipt) {
//        return receiptService.saveReceipt(receipt);
//    }

    @PostMapping(value = "/addReceipt")
    public ResponseEntity<Receipt> addReceipt(@RequestBody Receipt receipt) {
        receiptService.addReceipt(receipt);
        return ResponseEntity.ok(receipt);
    }

    @DeleteMapping(value = "/deleteReceipt")
    public ResponseEntity<HttpStatus> deleteReceipt(@RequestParam Long id) {
        receiptService.deleteReceipt(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}

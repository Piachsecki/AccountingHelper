package com.piasecki.controller;

import com.piasecki.domain.User;
import com.piasecki.service.FileService;
import com.piasecki.service.InvoiceService;
import com.piasecki.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@AllArgsConstructor
@RequestMapping("/api/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final FileService fileService;

    @PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> uploadFile(@RequestPart(value = "file") MultipartFile file) {
        fileService.uploadFile(file);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}

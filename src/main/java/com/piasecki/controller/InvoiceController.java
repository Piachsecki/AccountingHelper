package com.piasecki.controller;

import com.piasecki.domain.Invoice;
import com.piasecki.dto.InvoiceDTO;
import com.piasecki.service.FileService;
import com.piasecki.service.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



/*

Jak controller wykorzystuje 2 serwisy jednoczesnie w jednej metodzie, to
powinnismy stworzyc fasade, ktora to sobie rozdzieli/uzyhe
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final FileService fileService;

    @PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InvoiceDTO> uploadFile(@RequestPart(value = "file") MultipartFile file) {
        InvoiceDTO invoiceDTO = fileService.uploadInvoiceFile(file);
        return ResponseEntity.ok(invoiceDTO);
    }

    @PostMapping(value = "/addInvoice")
    public ResponseEntity<Invoice> addInvoice(@RequestBody Invoice invoice) {
        invoiceService.addInvoice(invoice);
        return ResponseEntity.ok(invoice);
    }

    @DeleteMapping(value = "/deleteInvoice")
    public ResponseEntity<HttpStatus> deleteInvoice(@RequestParam Long id) {
        invoiceService.deleteInvoice(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}

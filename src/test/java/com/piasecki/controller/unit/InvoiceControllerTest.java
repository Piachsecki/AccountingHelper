package com.piasecki.controller.unit;

import com.piasecki.controller.InvoiceController;
import com.piasecki.dto.InvoiceDTO;
import com.piasecki.service.FileService;
import com.piasecki.service.InvoiceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InvoiceControllerTest {

    @InjectMocks
    private InvoiceController invoiceController;

    @Mock
    private InvoiceService invoiceService;

    @Mock
    private FileService fileService;

    @Test
    public void shouldReturnInvoiceEntity() {
        //given
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        MultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "test".getBytes());

        //when
        when(fileService.uploadInvoiceFile(file)).thenReturn(invoiceDTO);
        ResponseEntity<InvoiceDTO> response = invoiceController.uploadFile(file);


        //then
        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        Assertions.assertEquals(invoiceDTO, response.getBody());

    }

    @Test
    public void shouldThrowAnErrorWhenFileServiceFails() {
        // given
        MultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "test".getBytes());
        when(fileService.uploadInvoiceFile(file)).thenThrow(new RuntimeException("Simulated file service failure"));

        // when, then
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            invoiceController.uploadFile(file);
        });

        Assertions.assertEquals("Simulated file service failure", exception.getMessage());
    }
}

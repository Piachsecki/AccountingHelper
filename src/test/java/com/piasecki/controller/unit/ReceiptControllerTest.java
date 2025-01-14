package com.piasecki.controller.unit;

import com.piasecki.controller.ReceiptController;
import com.piasecki.dto.ReceiptDTO;
import com.piasecki.service.FileService;
import com.piasecki.service.ReceiptService;
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
public class ReceiptControllerTest {

    @InjectMocks
    private ReceiptController receiptController;

    @Mock
    private ReceiptService receiptService;

    @Mock
    private FileService fileService;

    @Test
    public void shouldReturnInvoiceEntity() {
        //given
        ReceiptDTO receiptDTO = new ReceiptDTO();
        MultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "test".getBytes());

        //when
        when(fileService.uploadReceiptFile(file)).thenReturn(receiptDTO);
        ResponseEntity<ReceiptDTO> response = receiptController.uploadFile(file);


        //then
        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        Assertions.assertEquals(receiptDTO, response.getBody());

    }

    @Test
    public void shouldThrowAnErrorWhenFileServiceFails() {
        // given
        MultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "test".getBytes());
        when(fileService.uploadReceiptFile(file)).thenThrow(new RuntimeException("Simulated file service failure"));

        // when, then
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            receiptController.uploadFile(file);
        });

        Assertions.assertEquals("Simulated file service failure", exception.getMessage());
    }
}

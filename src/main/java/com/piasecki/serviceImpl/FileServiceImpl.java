package com.piasecki.serviceImpl;

import com.mindee.MindeeClient;
import com.mindee.input.LocalInputSource;
import com.mindee.parsing.common.AsyncPredictResponse;
import com.mindee.product.invoice.InvoiceV4;
import com.piasecki.domain.InvoiceType;
import com.piasecki.dto.InvoiceDTO;
import com.piasecki.dto.ReceiptDTO;
import com.piasecki.mapper.InvoiceMapper;
import com.piasecki.mapper.ReceiptMapper;
import com.piasecki.service.FileService;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.beans.Transient;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Objects;

@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService {

    private MinioClient minioClient;
    //    private InvoiceMapper invoiceMapper;
//    private ReceiptMapper receiptMapper;
    private MindeeClient mindeeClient;


    @Override
    public InvoiceDTO uploadInvoiceFile(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("File is empty");
            }
            uploadFileToMilo("invoices", file.getOriginalFilename(), file.getInputStream(), file.getContentType());

            Path destination = getPath(file);

            InvoiceDTO invoiceDTO = ocrInvoiceFileToObject(destination);


            return invoiceDTO;

        } catch (IOException e) {
            throw new RuntimeException("Store exception");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

//        return new InvoiceDTO();
    }

    private static @NotNull Path getPath(MultipartFile file) throws IOException {
        Path rootDir = Paths.get("src", "main", "resources", "static").toAbsolutePath().normalize();
        Path destination = rootDir.resolve(file.getOriginalFilename()).normalize().toAbsolutePath();
        Files.copy(file.getInputStream(), destination);
        return destination;
    }

    private InvoiceDTO ocrInvoiceFileToObject(Path destination) throws IOException, InterruptedException {
        LocalInputSource inputSource = new LocalInputSource(new File(destination.toString()));

        AsyncPredictResponse<InvoiceV4> response = mindeeClient.enqueueAndParse(
                InvoiceV4.class,
                inputSource
        );

        LocalDate issueDate = response.getDocument().get().getInference().getPrediction().getDate().getValue();
        LocalDate dueDate = response.getDocument().get().getInference().getPrediction().getDueDate().getValue();
        String invoiceNumber = response.getDocument().get().getInference().getPrediction().getInvoiceNumber().getValue();
        BigDecimal amount = BigDecimal.valueOf(response.getDocument().get().getInference().getPrediction().getTotalAmount().getValue());
        InvoiceType invoiceType = Objects.isNull(dueDate) ? InvoiceType.INCOME_INVOICE : InvoiceType.OUTGOING_INVOICE;

        return InvoiceDTO.builder()
                .invoiceNumber(invoiceNumber)
                .invoiceType(invoiceType)
                .issueDate(issueDate)
                .dueDate(dueDate)
                .amount(amount)
                .currency("PLN")
                .build();
    }

    @Override
    public ReceiptDTO uploadReceiptFile(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("File is empty");
            }
            uploadFileToMilo("receipts", file.getOriginalFilename(), file.getInputStream(), file.getContentType());

            return new ReceiptDTO();
        } catch (IOException e) {
            throw new RuntimeException("Store exception");
        }
    }


    public void uploadFileToMilo(String bucketName, String objectName, InputStream inputStream, String contentType) {
        try {
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
            minioClient.putObject(
                    PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(
                                    inputStream, inputStream.available(), -1)
                            .contentType(contentType)
                            .build());
        } catch (Exception e) {
            throw new RuntimeException("Error occurred: " + e.getMessage());
        }
    }


}

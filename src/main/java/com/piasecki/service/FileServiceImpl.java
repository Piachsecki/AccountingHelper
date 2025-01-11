package com.piasecki.service;

import com.mindee.MindeeClient;
import com.mindee.input.LocalInputSource;
import com.mindee.parsing.common.AsyncPredictResponse;
import com.mindee.parsing.common.PredictResponse;
import com.mindee.product.invoice.InvoiceV4;
import com.mindee.product.receipt.ReceiptV5;
import com.piasecki.domain.*;
import com.piasecki.dto.CompanyDTO;
import com.piasecki.dto.InvoiceDTO;
import com.piasecki.dto.ReceiptDTO;
import com.piasecki.mapper.InvoiceMapper;
import com.piasecki.mapper.ReceiptMapper;
import com.piasecki.utils.SecurityUtils;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileServiceImpl implements FileService {

    private final MinioClient minioClient;
    private final InvoiceMapper invoiceMapper;
    private final ReceiptMapper receiptMapper;
    private final ReceiptService receiptService;
    private final MindeeClient mindeeClient;
    private final InvoiceService invoiceService;
    private final UserService userService;




    @Override
    @Transactional
    public InvoiceDTO uploadInvoiceFile(MultipartFile file) {
        Path destination = null;
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("File is empty");
            }
            uploadFileToMilo("invoices", file.getOriginalFilename(), file.getInputStream(), file.getContentType());

            destination = addFileToProjectPath(file);

            InvoiceDTO invoiceDTO = ocrInvoiceFileToObject(destination);


            Invoice invoice = invoiceMapper.mapInvoiceDTOtoInvoiceEntity(invoiceDTO);
            User currentUser = SecurityUtils.getCurrentUser(userService);
            invoice.setUser(currentUser);

            invoiceService.addInvoice(invoice);
            deleteFile(destination);

            return invoiceDTO;
        } catch (IOException | InterruptedException e) {
            if (destination != null) {
                deleteFile(destination);
            }
            log.error("The file already exists under this path: [{}]", e.getMessage());
            throw new RuntimeException("Error during file upload and processing", e);
        }
    }

    public static void deleteFile(Path filePath){
        try {
            Files.deleteIfExists(filePath); // Deletes file if it ex8ists
        } catch (IOException e) {
            log.error("Couldn't delete file under path: [{}]", filePath.toString());
            throw new RuntimeException(e);
        }
    }


    private static @NotNull Path addFileToProjectPath(MultipartFile file) throws IOException {
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
        String currency = response.getDocument().get().getInference().getPrediction().getLocale().getCurrency();
        InvoiceType invoiceType = Objects.isNull(dueDate) ? InvoiceType.INCOME_INVOICE : InvoiceType.OUTGOING_INVOICE;

        BigDecimal taxRate = BigDecimal.valueOf(response.getDocument().get().getInference().getPrediction().getTaxes().get(0).getRate());

        String companyName = response.getDocument().get().getInference().getPrediction().getSupplierName().getValue();
        String companyAddress = response.getDocument().get().getInference().getPrediction().getSupplierAddress().getValue();
        String companyNIP = null;
        System.out.println(companyAddress);


        User currentUser = SecurityUtils.getCurrentUser(userService);

        List<String> findedNIPS = findNIPvalues(response.toString());
        if(findedNIPS.isEmpty()) {
            companyNIP = null;
        }else {
            for (String findedNIP : findedNIPS) {
                if(currentUser.getNIP().equals(findedNIP)){
                    continue;
                }else {
                    companyNIP = new String(findedNIP);
                    break;
                }
            }
        }

        CompanyDTO companyDTO = CompanyDTO.builder()
                .companyAddress(companyAddress)
                .companyName(companyName)
                .companyNIP(companyNIP)
                .build();


        return InvoiceDTO.builder()
                .invoiceNumber(invoiceNumber)
                .invoiceType(invoiceType)
                .issueDate(issueDate)
                .dueDate(dueDate)
                .amount(amount)
                .currency(currency)
                .companyDTO(companyDTO)
                .taxRate(taxRate)

                .build();
    }

    private List<String> findNIPvalues(String wholeInvoiceAsText) {

        // Wzorzec dla wyszukiwania NIP
        String regex = "\\b\\d{10}\\b|\\b\\d{3}-\\d{2}-\\d{2}-\\d{3}\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(wholeInvoiceAsText);

        // Lista do przechowywania znalezionych NIP-ów
        List<String> findedNIPS = new ArrayList<>();

        // Szukanie dopasowań
        while (matcher.find()) {
            if (!findedNIPS.contains(matcher.group()))
                findedNIPS.add(matcher.group());

        }

        return findedNIPS;
    }

    @Override
    public ReceiptDTO uploadReceiptFile(MultipartFile file) {
        Path destination = null;
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("File is empty");
            }
            uploadFileToMilo("receipts", file.getOriginalFilename(), file.getInputStream(), file.getContentType());

            destination = addFileToProjectPath(file);

            ReceiptDTO receiptDTO= ocrReceiptFileToObject(destination);

            Receipt receipt = receiptMapper.mapReceiptDTOtoReceiptEntity(receiptDTO);
            User currentUser = SecurityUtils.getCurrentUser(userService);
            receipt.setUser(currentUser);

            receiptService.addReceipt(receipt);


            deleteFile(destination);

            return receiptDTO;
        } catch (IOException | InterruptedException e) {
            if (destination != null) {
                deleteFile(destination);
            }
            log.error("The file already exists under this path: [{}]", e.getMessage());
            throw new RuntimeException("Error during file upload and processing", e);
        }
    }

    private ReceiptDTO ocrReceiptFileToObject(Path destination) throws IOException, InterruptedException {
        LocalInputSource inputSource = new LocalInputSource(new File(destination.toString()));

        PredictResponse<ReceiptV5> response = mindeeClient.parse(
                ReceiptV5.class,
                inputSource
        );

        LocalDate date = response.getDocument().getInference().getPrediction().getDate().getValue();
        BigDecimal amount = BigDecimal.valueOf(response.getDocument().getInference().getPrediction().getTotalAmount().getValue());
        String currency = response.getDocument().getInference().getPrediction().getLocale().getCurrency();
        Double rate = response.getDocument().getInference().getPrediction().getTaxes().get(0).getRate();
        BigDecimal taxRate = BigDecimal.ONE;
        if (Objects.nonNull(rate)){
            taxRate = BigDecimal.valueOf(rate);
        }
        boolean isNip = false;

        List<String> findedNIPS = findNIPvalues(response.toString());

        User currentUser = SecurityUtils.getCurrentUser(userService);

        for (String findedNIP : findedNIPS) {
            if (currentUser.getNIP().equals(findedNIP)) {
                isNip = true;
                break;
            }
        }


        return ReceiptDTO.builder()
                .isNip(isNip)
                .date(date)
                .currency(currency)
                .taxRate(taxRate)
                .amount(amount)
                .build();
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

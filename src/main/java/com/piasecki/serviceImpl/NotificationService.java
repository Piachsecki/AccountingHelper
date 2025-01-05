package com.piasecki.serviceImpl;


import com.piasecki.domain.Invoice;
import com.piasecki.domain.User;
import com.piasecki.service.InvoiceService;
import com.piasecki.service.UserService;
import com.piasecki.utils.SecurityUtils;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final JavaMailSender mailSender;
    private final InvoiceService invoiceService;
    private final UserService userService;

    @Value("$(spring.mail.username)")
    private String sender;

    @Scheduled(cron = "0 * * * * *")
    public void sendInvoiceNotification() {
        List<Invoice> costInvoices = invoiceService.getAllCostInvoices();
        User currentUser = SecurityUtils.getCurrentUser(userService);


        for (Invoice costInvoice : costInvoices) {
            if(costInvoice.getDueDate().equals(LocalDate.now().plusDays(3))) {
                try {
                    sendEmail(currentUser.getEmail(), costInvoice);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void sendEmail(String email, Invoice costInvoice) throws MessagingException {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(email);
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setSubject("Invoice to pay");
        simpleMailMessage.setText("This is a reminder that >3 days are left to pay the current invoice: " + costInvoice.getInvoiceNumber());

        mailSender.send(simpleMailMessage);
    }


}

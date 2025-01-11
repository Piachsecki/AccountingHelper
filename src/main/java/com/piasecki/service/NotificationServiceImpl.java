package com.piasecki.service;


import com.piasecki.domain.Notification;
import com.piasecki.domain.SendStatus;
import com.piasecki.repository.NotificationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional

public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    private final JavaMailSender mailSender;
//    private final InvoiceService invoiceService;
    private final UserService userService;

    @Value("$(spring.mail.username)")
    private String sender;


    private void sendEmail(Notification pendingNotification, String email){
        log.info("Email sent to: [{}]\nThis is a reminder that < 3 days are left to pay the current invoice: [{}]", email, pendingNotification.getInvoiceNumber());
        try {

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(email);
            simpleMailMessage.setFrom(sender);
            simpleMailMessage.setSubject("Invoice to pay");
            simpleMailMessage.setText("This is a reminder that < 3 days are left to pay the current invoice: [" + pendingNotification.getInvoiceNumber() + "]");
            mailSender.send(simpleMailMessage);

            pendingNotification.setSendStatus(SendStatus.SENT);
            updateNotification(pendingNotification);
            log.info("Email sent successfully and notification has been updated to SENT [{}, {}]",
                    pendingNotification.getInvoiceNumber(),
                    pendingNotification.getSendStatus()
            );

        }catch (MailException exception){
            pendingNotification.setSendStatus(SendStatus.ERROR);
            updateNotification(pendingNotification);
            log.error("Exception while sending email [{}]", exception.getMessage());
        }
    }


    @Override
    public void addNotification(Notification notification) {
        boolean exists = notificationRepository.existsByInvoiceNumber(notification.getInvoiceNumber());
        if (exists) {
            log.error("Wanted to add a notification that already exists! [{}]", notification.getInvoiceNumber());
            throw new IllegalArgumentException("Notification with the same invoice number already exists: " + notification.getInvoiceNumber());
        }
        notificationRepository.save(notification);
    }


    @Override
//    @Scheduled(cron = "0 0 10 * * Mon")
    @Scheduled(cron = "0 * * * * *")
    public void checkIfThereIsNotificationToSend() {
        log.info("checkIfThereIsNotificationToSend()");
        List<Notification> allBySendStatus = notificationRepository.getAllBySendStatus(SendStatus.PENDING);
        for (Notification pendingNotification : allBySendStatus) {
            System.out.println("pendingNotification = " + pendingNotification.getId());
            String usersEmail = userService.findUserById(pendingNotification.getUserId()).getEmail();
//            Invoice findedInvoice = invoiceService.getInvoiceByInvoiceNumber(pendingNotification.getInvoiceNumber());
            sendEmail(pendingNotification, usersEmail);
        }
    }


/*
    //TODO - WAZNE
    Czy to powinno byc rowniez scheduled? Czy zrobic 1 metode usuwajaca SENT, po tym jak sent email bedzie successfull
 */
    @Override
    public void deleteAllSentNotifications() {
        notificationRepository.deleteAllBySendStatus(SendStatus.SENT);
    }

    @Override
    public Notification updateNotification(Notification notification) {
        return notificationRepository.save(notification);
    }
}

/*


        2 serwisy shchedulingowe:
        1 - sprawdza invocies periodycznie (metoda Scheduled w serwisie InvoiceService)
        2 - ktory periodycznie sprawdza notyfikacje (statusy) z bazy danych

        Stworzyc klase NotificationEntity(i typowe dla niej   jak serwis - selecty itp, repo - itp) i tam typowe rzeczy utworzyc w tej bazie jak numer id uzytkownika
         numer faktury do zaplacenia i domyslnie ona wrzucic powinna bez statusu i tam domyslnie w bazie danych powinan byc ustawiona na PENDING,




         serwis smtp - uzyc jakiegos innego serwisy niz GOOGLE!!!!!

         SERWIS smtp - proton mail  - sprawdz i uzyj

         A drugi serwis ktory juz przetyworzy wszystko




        Potem jak invoiceService dodaje event do systemuNotyfikacji i tam jest metoda sprawdzajaca 1 raz dziennie np. czy jest cos do wyslania


        Problem pojawia sie gdy wysylania notyfikacji wyrzuci fail  - i co wtedy zrobic - czy ponownie postarac sie wyslac,
        czy co zrobic dokladnie



        Powininenem miec tabele w bazie danych z notyfikacjami - jak cos sie dzieje w aplikacji i jakis user powinien zostac
        powiadomiony, to wtedy z bazy danych otrzymujemy jakis status  - wait/ PENDING/ SENT/ , o raz nr uzytkownika do ktorego ta wiadomosc
        ma byc wyslana i jak cos potem zaczyna wysylac



        2 serwis schedulingowy - ktory bedzie przegladal baze danych invoices i sprawdzal co ma byc powiadomonie i jak jest to do wyslania
        to wysyla to do notificationService ze statusem NP. PENDING

        ten notification service ma kolejna funckje typu checkStatus - i kazda ktora jest w stanie PENDING/WAIT to powinna ja wysylac

*/
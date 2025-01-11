package com.piasecki.repository;

import com.piasecki.domain.Notification;
import com.piasecki.domain.SendStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> getAllBySendStatus(SendStatus sendStatus);
    void deleteAllBySendStatus(SendStatus sendStatus);
    boolean existsByInvoiceNumber(String invoiceNumber);

}

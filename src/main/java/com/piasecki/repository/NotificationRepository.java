package com.piasecki.repository;

import com.piasecki.domain.Notification;
import com.piasecki.domain.SEND_STATUS;
import org.aspectj.weaver.ast.Not;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> getAllBySendStatus(SEND_STATUS sendStatus);
    void deleteAllBySendStatus(SEND_STATUS sendStatus);
    boolean existsByInvoiceNumber(String invoiceNumber);

}

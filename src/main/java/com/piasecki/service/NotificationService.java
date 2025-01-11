package com.piasecki.service;

import com.piasecki.domain.Notification;
import com.piasecki.domain.SEND_STATUS;

public interface NotificationService {
    void addNotification(Notification notification);
    void checkIfThereIsNotificationToSend();
    void deleteAllSentNotifications();
    Notification updateNotification(Notification notification);
}


/*
SERWIS I JEGO IMPLEMENTACJA MUSI BYC W TYM SAMYM PAKIECIE!!!!!
 */
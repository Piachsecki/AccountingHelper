package com.piasecki.service;

import com.piasecki.domain.*;
import com.piasecki.exception.InvoiceNotFoundException;
import com.piasecki.repository.InvoiceRepository;
import com.piasecki.utils.SecurityUtils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final UserService userService;

    private final NotificationService notificationService;

    @Override
    public Invoice getInvoice(long id) {
        return invoiceRepository.findById(id).get();
    }

    @Override
    public List<Invoice> getAllInvoices() {
        User currentUser = SecurityUtils.getCurrentUser(userService);
        return invoiceRepository.findAllByUserId(currentUser.getId());
    }

    @Override
    public Invoice addInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    public void deleteInvoice(long id) {
        invoiceRepository.deleteById(id);
    }

    @Override
    public void updateInvoice(long id, Invoice invoice) {

    }

    @Override
    public List<Invoice> getAllCostInvoices() {
        User currentUser = SecurityUtils.getCurrentUser(userService);
        return invoiceRepository.findAllByUserIdAndInvoiceType(currentUser.getId(), InvoiceType.OUTGOING_INVOICE);
    }

    @Override
    public List<Invoice> getAllIncomeInvoices() {
        User currentUser = SecurityUtils.getCurrentUser(userService);
        return invoiceRepository.findAllByUserIdAndInvoiceType(currentUser.getId(), InvoiceType.INCOME_INVOICE);
    }

    @Override
//    @Scheduled(cron = "0 0 9 * * Mon")
    @Scheduled(cron = "0 * * * * *")
    @Transactional
    public void checkIfShouldBeNotified() {
        log.info("checkIfShouldBeNotified()");

        List<Long> allUserIds = userService.findAllIds();
        for (Long userId : allUserIds) {
            List<Invoice> usersInvoicesToPay = invoiceRepository
                    .findAllByUserIdAndInvoiceType(
                            userId,
                            InvoiceType.OUTGOING_INVOICE);
            for (Invoice invoiceToNotify : usersInvoicesToPay) {
                System.out.println("invoice to notify: " + invoiceToNotify.getInvoiceNumber());
                if (LocalDate.now().plusDays(3).equals(invoiceToNotify.getDueDate()) ||
                        LocalDate.now().plusDays(3).isAfter(invoiceToNotify.getDueDate())

                ){
                    try {
                        notificationService.addNotification(Notification.builder()
                                .invoiceNumber(invoiceToNotify.getInvoiceNumber())
                                .sendStatus(SendStatus.PENDING)
                                .userId(userId)
                                .build());
                        log.info("Saved notification with invoiceNumber: [{}]", invoiceToNotify.getInvoiceNumber());
                    } catch (IllegalArgumentException e) {
                        log.error("[{}] : Addinng the notification with the same invoice number: [{}]", e.getMessage(), invoiceToNotify.getInvoiceNumber());
                    }
                }
            }
        }
    }

    @Override
    public Invoice getInvoiceByInvoiceNumber(String invoiceNumber) {
        Optional<Invoice> invoiceByInvoiceNumber = invoiceRepository.findInvoiceByInvoiceNumber(invoiceNumber);
        if (invoiceByInvoiceNumber.isPresent()) {
            return invoiceByInvoiceNumber.get();
        }
        log.error("There is no invoice with the specified id: [{}]", invoiceNumber);
        throw new InvoiceNotFoundException(invoiceNumber);
    }

    @Override
    public List<Invoice> getAllIncomeInvoicesByDate(LocalDate date) {
        User currentUser = SecurityUtils.getCurrentUser(userService);
        LocalDate deadlineMonth = LocalDate.of(date.getYear(), date.getMonth().getValue() + 1, 1);
        return invoiceRepository.findAllByUserIdAndInvoiceTypeAndIssueDateBetween(currentUser.getId(), InvoiceType.INCOME_INVOICE, date, deadlineMonth);
    }

    @Override
    public List<Invoice> getAllCostInvoicesByDate(LocalDate date) {
        User currentUser = SecurityUtils.getCurrentUser(userService);
        LocalDate deadlineMonth = LocalDate.of(date.getYear(), date.getMonth().getValue() + 1, 1);
        return invoiceRepository.findAllByUserIdAndInvoiceTypeAndIssueDateBetween(currentUser.getId(), InvoiceType.OUTGOING_INVOICE, date, deadlineMonth);
    }
}

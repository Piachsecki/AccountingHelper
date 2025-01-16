package com.piasecki.repository;

import com.piasecki.domain.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    List<Receipt> findAllByUserId(Long userId);
    List<Receipt> findAllByUserIdAndAndDateBetween(Long userId, LocalDate from, LocalDate to);
}

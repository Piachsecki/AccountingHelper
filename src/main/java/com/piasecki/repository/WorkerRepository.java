package com.piasecki.repository;

import com.piasecki.domain.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {
    List<Worker> getAllByUserId(Long id);
}

package com.piasecki.service;

import com.piasecki.domain.Worker;
import com.piasecki.dto.WorkerDTO;

import java.util.List;

public interface WorkerService {
    WorkerDTO addWorker(Worker worker);
    void deleteWorkerBySurname(String surname);
    List<Worker> getAllWorkers();
}

package com.piasecki.service;

import com.piasecki.domain.User;
import com.piasecki.domain.Worker;
import com.piasecki.dto.WorkerDTO;
import com.piasecki.repository.WorkerRepository;
import com.piasecki.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkerServiceImpl implements WorkerService {
    private final WorkerRepository workerRepository;
    private final UserService userService;




    /*
    Dorobic WorkerMapper i tutaj uzyc zamiast manualnie to robic:
     */
    @Override
    public WorkerDTO addWorker(Worker worker) {
        User currentUser = SecurityUtils.getCurrentUser(userService);
        worker.setUser(currentUser);
        Worker savedWorker = workerRepository.save(worker);
        return WorkerDTO.builder()
                .salary(savedWorker.getSalary())
                .surname(savedWorker.getSurname())
                .name(savedWorker.getName())
                .build();
    }

    @Override
    public void deleteWorkerBySurname(String surname) {

    }

    @Override
    public List<Worker> getAllWorkers() {
        User currentUser = SecurityUtils.getCurrentUser(userService);
        return workerRepository.getAllByUserId(currentUser.getId());
    }
}

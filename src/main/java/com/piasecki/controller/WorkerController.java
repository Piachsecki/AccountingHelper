package com.piasecki.controller;


import com.piasecki.domain.Worker;
import com.piasecki.dto.InvoiceDTO;
import com.piasecki.dto.WorkerDTO;
import com.piasecki.service.WorkerService;
import com.piasecki.service.WorkerServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@AllArgsConstructor
@RequestMapping("/api/worker")
public class WorkerController {
    private WorkerService workerService;


    @PostMapping(value = "/addWorker")
    public ResponseEntity<WorkerDTO> addWorker(@RequestBody Worker worker) {
        WorkerDTO workerDTO = workerService.addWorker(worker);
        return ResponseEntity.ok(workerDTO);
    }

}

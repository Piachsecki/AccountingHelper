package com.piasecki.controller;

import com.piasecki.domain.Worker;
import com.piasecki.dto.WorkerDTO;
import com.piasecki.service.UserService;
import com.piasecki.service.WorkerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserControllerForDBTests {
    private UserService userService;


    @DeleteMapping(value = "/delete")
    public ResponseEntity<HttpStatus> addWorker(@RequestParam(value = "id", required = true) Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }


}

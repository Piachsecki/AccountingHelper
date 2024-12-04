package com.piasecki.controller;

import com.piasecki.domain.User;
import com.piasecki.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userService.addUser(user);
    }


//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public User deleteUser(@RequestParam) {
//        return userService.addUser(user);
//    }
}

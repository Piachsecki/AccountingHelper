package com.piasecki.service;

import com.piasecki.domain.User;
import jakarta.validation.Valid;

import java.util.List;

public interface UserService {
    User addUser(@Valid User user);
    User findByUsername(String username);
    List<Long> findAllIds();
    User findUserById(Long id);
    void deleteUserById(Long id);

}

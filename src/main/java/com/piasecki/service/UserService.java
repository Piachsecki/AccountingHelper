package com.piasecki.service;

import com.piasecki.domain.User;

import java.util.List;

public interface UserService {
    User addUser(User user);
    User findByUsername(String username);
    List<Long> findAllIds();
    User findUserById(Long id);

}

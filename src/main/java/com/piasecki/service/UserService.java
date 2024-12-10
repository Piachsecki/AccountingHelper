package com.piasecki.service;

import com.piasecki.domain.User;

public interface UserService {
    User addUser(User user);
    User findByUsername(String username);
}

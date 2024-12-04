package com.piasecki.service;

import com.piasecki.domain.User;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

public interface UserService {
    User addUser(User user);
}

package com.piasecki.service;

import com.piasecki.domain.User;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;

public interface UserService {
    User addUser(User user);
    User findByUsername(String username);
}

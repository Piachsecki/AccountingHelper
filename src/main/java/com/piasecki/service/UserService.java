package com.piasecki.service;

import com.piasecki.domain.User;
import org.springframework.stereotype.Service;

public interface UserService {
    User addUser(User user);
}

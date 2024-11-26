package com.piasecki.serviceImpl;

import com.piasecki.domain.User;
import com.piasecki.repository.UserRepository;
import com.piasecki.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(@Valid User user) {
        return userRepository.save(user);
    }
}

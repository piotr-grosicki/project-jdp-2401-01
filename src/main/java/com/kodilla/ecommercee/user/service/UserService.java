package com.kodilla.ecommercee.user.service;

import com.kodilla.ecommercee.user.domain.User;
import com.kodilla.ecommercee.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUser(final Long userId) {
        return userRepository.findByUserId(userId);
    }
}

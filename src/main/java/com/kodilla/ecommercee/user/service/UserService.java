package com.kodilla.ecommercee.user.service;

import com.kodilla.ecommercee.user.controller.UserNotFoundException;
import com.kodilla.ecommercee.user.domain.User;
import com.kodilla.ecommercee.user.domain.UserDto;
import com.kodilla.ecommercee.user.domain.UserStatus;
import com.kodilla.ecommercee.user.domain.UserUpdateStatusDto;
import com.kodilla.ecommercee.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUser(final Long userId) {
        return userRepository.findByUserId(userId);
    }

    public void createUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setStatusEnum(UserStatus.ACTIVE);
        userRepository.save(user);
    }

    public void changeUserStatus(UserUpdateStatusDto updateStatusDto) throws UserNotFoundException {
        User user = userRepository.findById(updateStatusDto.getUserId())
                .orElseThrow(UserNotFoundException::new);
        user.setStatusEnum(updateStatusDto.getUserStatus());
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User getUserById(Long userId) throws UserNotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
    }

    public void updateUser(Long userId, UserDto userDto) throws UserNotFoundException {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        existingUser.setUsername(userDto.getUsername());
        existingUser.setPassword(userDto.getPassword());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setStatusEnum(userDto.getUserStatus());
        userRepository.save(existingUser);
    }

    public void updateUserStatus(Long userId, UserUpdateStatusDto updateStatusDto) throws UserNotFoundException {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        existingUser.setStatusEnum(updateStatusDto.getUserStatus());
        userRepository.save(existingUser);
    }

    public void deleteUser(Long userId) throws UserNotFoundException {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        userRepository.delete(existingUser);
    }

    public void lockUser(Long userId) throws UserNotFoundException {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        existingUser.setStatusEnum(UserStatus.LOCKED);
        userRepository.save(existingUser);
    }

    public String generateRandomKey() {
        Random random = new Random();
        byte[] keyBytes = new byte[10];
        random.nextBytes(keyBytes);
        StringBuilder stringBuilder = new StringBuilder();
        for (byte keyByte : keyBytes) {
            stringBuilder.append(String.format("%02X", keyByte));
        }
        return stringBuilder.toString();
    }

    public void generateKeyOneHourAfterSuccessfulLogin(Long userId) throws UserNotFoundException {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        LocalDateTime expirationTime = LocalDateTime.now().plusHours(1);
        existingUser.setKeyExpirationTime(expirationTime);
        existingUser.setGeneratedKey(generateRandomKey());
        userRepository.save(existingUser);
    }
}


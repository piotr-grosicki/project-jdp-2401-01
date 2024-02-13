package com.kodilla.ecommercee.user.controller;

import com.kodilla.ecommercee.user.mapper.UserMapper;
import com.kodilla.ecommercee.user.domain.User;
import com.kodilla.ecommercee.user.domain.UserDto;
import com.kodilla.ecommercee.user.domain.UserUpdateStatusDto;
import com.kodilla.ecommercee.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserDto> userDtos = users.stream()
                .map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userDtos);
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto) {
        userService.createUser(userDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId) throws UserNotFoundException {
        User user = userService.getUserById(userId);
        UserDto userDto = UserMapper.mapToUserDto(user);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) throws UserNotFoundException {
        userService.updateUser(userId, userDto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<Void> updateUserStatus(@PathVariable Long userId, @RequestBody UserUpdateStatusDto updateStatusDto) throws UserNotFoundException {
        userService.updateUserStatus(userId, updateStatusDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) throws UserNotFoundException {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{userId}/lock")
    public ResponseEntity<Void> lockUser(@PathVariable Long userId) {
        try {
            userService.lockUser(userId);
            return ResponseEntity.ok().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{userId}/generate-key")
    public ResponseEntity<Void> generateKeyOneHourAfterSuccessfulLogin(@PathVariable Long userId) {
        try {
            userService.generateKeyOneHourAfterSuccessfulLogin(userId);
            return ResponseEntity.ok().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

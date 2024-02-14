package com.kodilla.ecommercee.user.controller;

import com.kodilla.ecommercee.user.domain.UserDto;
import com.kodilla.ecommercee.user.domain.UserUpdateStatusDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class  UserController {

    @PostMapping
    public void createUser(@RequestBody final UserDto userDto) {
        System.out.println("Creating user");
    }

    @PatchMapping
    public void changeUserStatus(@RequestBody final UserUpdateStatusDto updateStatus) {
        System.out.println("Updating user status");
    }

    @PostMapping(value = "{auth}")
    public String authenticate(@PathVariable String auth) {
        return "Authenticating user... User authenticated, token: 12345";
    }
}

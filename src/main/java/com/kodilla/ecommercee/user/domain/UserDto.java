package com.kodilla.ecommercee.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long userId;
    private String username;
    private String password;
    private String email;
    private UserStatus userStatus;
}

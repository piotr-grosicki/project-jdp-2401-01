package com.kodilla.ecommercee.user.mapper;

import com.kodilla.ecommercee.user.domain.User;
import com.kodilla.ecommercee.user.domain.UserDto;
import com.kodilla.ecommercee.user.domain.UserStatus;
import com.kodilla.ecommercee.user.domain.UserUpdateStatusDto;

public class UserMapper {

    public static User mapToUser(UserDto userDto) {
        return User.builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .statusEnum(UserStatus.ACTIVE)
                .build();
    }
    public static UserUpdateStatusDto mapToUpdateStatusDto(User user) {
        return new UserUpdateStatusDto(user.getUserId(), (UserStatus) user.getStatusEnum());
    }

    public static UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getUserId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                (UserStatus) user.getStatusEnum()
        );
    }
}

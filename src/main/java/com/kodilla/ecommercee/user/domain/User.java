package com.kodilla.ecommercee.user.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "USERS")
public class User {

    @Id
    private Long userId;
}

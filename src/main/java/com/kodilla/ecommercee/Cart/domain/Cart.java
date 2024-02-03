package com.kodilla.ecommercee.Cart.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "CARTS")
public class Cart {

    @Id
    private Long cartId;
}

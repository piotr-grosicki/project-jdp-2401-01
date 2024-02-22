package com.kodilla.ecommercee.cart.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
@Getter
@AllArgsConstructor
public class CartDto {
    private Long cartId;
    private Long userId;
    private List<Long> productIds;
}

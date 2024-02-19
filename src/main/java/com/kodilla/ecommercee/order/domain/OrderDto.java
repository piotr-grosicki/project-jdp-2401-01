package com.kodilla.ecommercee.order.domain;

import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private long orderId;
    private User user;
    private Cart cart;
    private BigDecimal value;
}

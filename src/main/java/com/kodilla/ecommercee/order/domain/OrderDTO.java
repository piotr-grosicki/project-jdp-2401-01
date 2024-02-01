package com.kodilla.ecommercee.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderDTO {
    private int orderId;
    private String userName;
}

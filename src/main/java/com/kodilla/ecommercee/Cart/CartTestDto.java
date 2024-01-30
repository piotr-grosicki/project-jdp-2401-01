package com.kodilla.ecommercee.Cart;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
@Getter
@AllArgsConstructor
public class CartTestDto {
    private int cartId;
    private String username;
    private List<ProductTest> productsInCart;
}

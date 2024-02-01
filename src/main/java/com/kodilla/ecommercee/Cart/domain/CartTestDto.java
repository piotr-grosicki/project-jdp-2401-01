package com.kodilla.ecommercee.Cart.domain;

import com.kodilla.ecommercee.product.domain.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
@Getter
@AllArgsConstructor
public class CartTestDto {
    private int cartId;
    private String username;
    private List<ProductDto> productsInCart;
}

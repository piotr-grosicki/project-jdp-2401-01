package com.kodilla.ecommercee.cart.Domain;

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

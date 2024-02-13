package com.kodilla.ecommercee.cart.mapper;

import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.cart.domain.CartDto;
import com.kodilla.ecommercee.user.domain.User;
import com.kodilla.ecommercee.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartMapper {

    private final UserService userService;

    public Cart mapToCart(final CartDto cartDto) {
        User user = userService.getUser(cartDto.getUserId());
        return new Cart(cartDto.getCartId(), user, cartDto.getProducts());
    }

    public CartDto mapToCartDto(final Cart cart) {
        return new CartDto(cart.getCartId(), cart.getUser().getUserId(), cart.getProducts());
    }
}

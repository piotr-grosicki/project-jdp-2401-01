package com.kodilla.ecommercee.cart.mapper;

import com.kodilla.ecommercee.cart.controller.CartNotFoundException;
import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.cart.domain.CartDto;
import com.kodilla.ecommercee.cart.service.CartService;
import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.user.domain.User;
import com.kodilla.ecommercee.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartMapper {

    private final UserService userService;
    private final CartService cartService;

    public Cart mapToCart(final CartDto cartDto) throws CartNotFoundException {
        User user = userService.getUser(cartDto.getUserId());
        List<Product> productsList = cartService.getProductsFromCart(cartDto.getCartId());
        return new Cart(cartDto.getCartId(), user, productsList);
    }

    public CartDto mapToCartDto(final Cart cart) {
        return new CartDto(cart.getCartId(), cart.getUser().getUserId(), cart.getProducts().stream()
                .map(Product::getProductId).collect(Collectors.toList()));
    }
}

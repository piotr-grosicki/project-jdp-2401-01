package com.kodilla.ecommercee.cart.service;

import com.kodilla.ecommercee.cart.controller.CartNotFoundException;
import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.cart.repository.CartRepository;
import com.kodilla.ecommercee.order.domain.Order;
import com.kodilla.ecommercee.product.controller.ProductNotFoundException;
import com.kodilla.ecommercee.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public Cart addCart(final Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart getCart(final Long cartId) throws CartNotFoundException {
        return cartRepository.findByCartId(cartId).orElseThrow(CartNotFoundException::new);
    }

    public List<Product> getProductsFromCart(final Long cartId) throws CartNotFoundException {
        List<Product> productsList = cartRepository.findByCartId(cartId).map(Cart::getProducts)
                .orElseThrow(CartNotFoundException::new);
        return productsList;
    }

    public Product getProductFromCart(Long cartId, Long productId) throws CartNotFoundException, ProductNotFoundException {
        Cart cart = cartRepository.findByCartId(cartId).orElseThrow(CartNotFoundException::new);
        for (Product product: cart.getProducts()) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        throw new ProductNotFoundException();
    }

    public Cart addProductToCart(final Long cartId, final Product product) throws CartNotFoundException {
        Cart cart = cartRepository.findByCartId(cartId).orElseThrow(CartNotFoundException::new);
        cart.getProducts().add(product);
        return cartRepository.save(cart);
    }

    public Cart deleteProductFromCart(final Long cartId, final Product product) throws CartNotFoundException {
        Cart cart = cartRepository.findByCartId(cartId).orElseThrow(CartNotFoundException::new);
        cart.getProducts().remove(product);
        return cartRepository.save(cart);
    }

    public Order createOrderFromCart(final Cart cart) {
        return Order.builder()
                .cart(cart)
                .user(cart.getUser())
                .orderValue(calculateOrderValue(cart))
                .build();
    }

    private BigDecimal calculateOrderValue(final Cart cart) {
        BigDecimal total = BigDecimal.ZERO;
        for (Product product: cart.getProducts()) {
            total = total.add(product.getPrice());
        }
        return total;
    }
}

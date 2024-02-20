package com.kodilla.ecommercee.cart.controller;

import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.cart.domain.CartDto;
import com.kodilla.ecommercee.cart.mapper.CartMapper;
import com.kodilla.ecommercee.cart.service.CartService;
import com.kodilla.ecommercee.order.domain.Order;
import com.kodilla.ecommercee.order.service.OrderService;
import com.kodilla.ecommercee.product.controller.ProductNotFoundException;
import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.product.domain.ProductDto;
import com.kodilla.ecommercee.product.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartMapper cartMapper;
    private final CartService cartService;
    private final ProductMapper productMapper;
    private final OrderService orderService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCart(@RequestBody CartDto cartDto) throws CartNotFoundException {
        Cart cart = cartMapper.mapToCart(cartDto);
        cartService.addCart(cart);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{cartId}")
    public ResponseEntity<List<ProductDto>> getProductsFromCart(@PathVariable Long cartId) throws CartNotFoundException {
        List<Product> productsList = cartService.getProductsFromCart(cartId);
        return ResponseEntity.ok(productMapper.mapToProductDtoList(productsList));
    }

    @PutMapping("/add/{cartId}")
    public ResponseEntity<CartDto> addProductToCart(@PathVariable Long cartId, @RequestBody ProductDto productDto) throws CartNotFoundException, ProductNotFoundException {
        Product product = productMapper.mapToProduct(productDto);
        Cart cart = cartService.addProductToCart(cartId, product);
        return ResponseEntity.ok(cartMapper.mapToCartDto(cart));
    }

    @PutMapping("/delete/{cartId}/{productId}")
    public ResponseEntity<CartDto> deleteProductFromCart(@PathVariable Long cartId, @PathVariable Long productId) throws CartNotFoundException, ProductNotFoundException {
        Product product = cartService.getProductFromCart(cartId, productId);
        Cart cart = cartService.deleteProductFromCart(cartId, product);
        return ResponseEntity.ok(cartMapper.mapToCartDto(cart));
    }

    @PostMapping("/order/{cartId}")
    public ResponseEntity<Void> createOrderFromCart(@PathVariable Long cartId) throws CartNotFoundException {
        Cart cart = cartService.getCart(cartId);
        Order order = cartService.createOrderFromCart(cart);
        orderService.saveOrder(order);
        return ResponseEntity.ok().build();
    }
}

package com.kodilla.ecommercee.Cart.controller;

import com.kodilla.ecommercee.Cart.domain.CartTestDto;
import com.kodilla.ecommercee.order.domain.OrderDto;
import com.kodilla.ecommercee.product.domain.ProductDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/cart")
public class CartController {
    @PostMapping()
    public void createCart(@RequestBody CartTestDto cartTestDto){
        System.out.println("cart created");
    }
    @GetMapping("{cartId}")
    public List<ProductDto> getProductsFromCart(@PathVariable int cartId){
        return new ArrayList<>();
    }
    @PutMapping("/add/{cartId}/{productId}")
    public CartTestDto addProductToCart(@PathVariable int cartId, @PathVariable int productId){
        return new CartTestDto(1,"username",new ArrayList<>());
    }
    @PutMapping("/delete/{cartId}/{productId}")
    public CartTestDto deleteProductFromCart(@PathVariable int cartId, @PathVariable int productId){
        return new CartTestDto(1,"username",new ArrayList<>());
    }
    @PostMapping("/order/{cartId}")
    public OrderDto createOrderFromCart(@PathVariable int cartId){
        return new OrderDto(cartId, "username");
    }
}

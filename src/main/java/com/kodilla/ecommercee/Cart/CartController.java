package com.kodilla.ecommercee.Cart;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/cart")
public class CartController {
    @PostMapping("/cart")
    public void createCart(@RequestBody CartTestDto cartTestDto){
        System.out.println("cart created");
    }
    @GetMapping
    public List<ProductTest> getProductsFromCart(@RequestBody CartTestDto cartTestDto){
        return cartTestDto.getProductsInCart();
    }
    @PutMapping("/add/{cartId}/{productId}")
    public CartTestDto addProductToCart(@PathVariable int cartId, @PathVariable int productId){
        return new CartTestDto(1,"username",new ArrayList<>());
    }
    @PutMapping("/delete/{cartId}/{productId}")
    public CartTestDto deleteProductFromCart(@PathVariable int cartId, @PathVariable int productId){
        return new CartTestDto(1,"username",new ArrayList<>());
    }
    @PostMapping("/order")
    public OrderTempDto createOrderFromCart(@RequestBody CartTestDto cartTestDto){
        return new OrderTempDto(1, cartTestDto.getCartId(), cartTestDto.getUsername(), cartTestDto.getProductsInCart());
    }
}

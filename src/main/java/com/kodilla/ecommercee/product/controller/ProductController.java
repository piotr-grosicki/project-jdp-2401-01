package com.kodilla.ecommercee.product.controller;

import com.kodilla.ecommercee.product.domain.ProductDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public List<ProductDto> getProducts() {
        List<ProductDto> listOfProducts = new ArrayList<>();
        listOfProducts.add(new ProductDto(10L, "Product1", "Product 1 description", 10.99));
        listOfProducts.add(new ProductDto(11L, "Product2", "Product 2 description", 15.99));
        listOfProducts.add(new ProductDto(12L, "Product3", "Product 3 description", 19.99));
        return listOfProducts;
    }

    @GetMapping(value = "{productId}")
    public ProductDto getProduct(@PathVariable final Long productId) {
        return new ProductDto(1L, "Koc", "Ciepły koc", 9.99);
    }

    @PostMapping
    public void createProduct(@RequestBody final ProductDto productDto) {
        System.out.println("Creating product");
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody final ProductDto productDto) {
        return new ProductDto(2L, "Zaktualizowany koc", "Cieplejszy koc", 12.99);
    }

    @DeleteMapping(value = "{productId}")
    public void deleteProduct(@PathVariable final Long productId) {
        System.out.println("Deleteing product");
    }
}

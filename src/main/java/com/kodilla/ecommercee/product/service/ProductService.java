package com.kodilla.ecommercee.product.service;

import com.kodilla.ecommercee.product.controller.ProductNotFoundException;
import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(final Long productId) throws ProductNotFoundException {
        return productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
    }

    public Product saveProduct(final Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(final Long productId) throws ProductNotFoundException {
        try {
            productRepository.deleteById(productId);
        } catch (Exception e) {
            throw new ProductNotFoundException();
        }
    }
}

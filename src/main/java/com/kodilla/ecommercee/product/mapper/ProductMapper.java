package com.kodilla.ecommercee.product.mapper;

import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.product.domain.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductMapper {

    public Product mapToProduct(ProductDto productDto) {
        return new Product(productDto.getProductId(), productDto.getProductGroups(), productDto.getName(),
                productDto.getPrice(), productDto.getCartsList());
    }

    public ProductDto mapToProductDto(Product product) {
        return new ProductDto(product.getProductId(), product.getProductGroups(), product.getName(),
                product.getPrice(), product.getCarts());
    }

    public List<ProductDto> mapToProductDtoList(List<Product> productsList) {
        return productsList.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
    }
}

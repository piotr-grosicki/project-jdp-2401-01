package com.kodilla.ecommercee.product.mapper;

import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.product.domain.ProductDto;
import com.kodilla.ecommercee.productGroup.domain.ProductGroups;
import com.kodilla.ecommercee.productGroup.service.ProductGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductMapper {

    private final ProductGroupService productGroupService;

    public Product mapToProduct(ProductDto productDto) {
        ProductGroups group = productGroupService.getProductGroupById(productDto.getProductGroupId()).orElseThrow(NullPointerException::new);
        return Product.builder()
                .productId(productDto.getProductId())
                .productGroups(group)
                .name(productDto.getName())
                .price(productDto.getPrice())
                .build();
    }

    public ProductDto mapToProductDto(Product product) {
        return new ProductDto(product.getProductId(), product.getProductGroups().getId(), product.getName(),
                product.getPrice());
    }

    public List<ProductDto> mapToProductDtoList(List<Product> productsList) {
        return productsList.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
    }
}

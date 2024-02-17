package com.kodilla.ecommercee.product.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long productId;
    private Long productGroupId;
    private String name;
    private BigDecimal price;
    private List<Long> cartsIdsList = new ArrayList<>();
}

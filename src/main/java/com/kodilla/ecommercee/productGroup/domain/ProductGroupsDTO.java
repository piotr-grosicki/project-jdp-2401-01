package com.kodilla.ecommercee.productGroup.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductGroupsDTO {

    private Long id;
    private String name;
    private String description;
}

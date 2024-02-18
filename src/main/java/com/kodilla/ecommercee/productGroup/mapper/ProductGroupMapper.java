package com.kodilla.ecommercee.productGroup.mapper;

import com.kodilla.ecommercee.productGroup.domain.ProductGroups;
import com.kodilla.ecommercee.productGroup.domain.ProductGroupsDTO;
import org.springframework.stereotype.Service;

@Service
public class ProductGroupMapper {

    public ProductGroups mapToProductGroups(final ProductGroupsDTO productGroupsDTO){
        return ProductGroups.builder()
                .id(productGroupsDTO.getId())
                .name(productGroupsDTO.getName())
                .description(productGroupsDTO.getDescription())
                .build();
    }

    public ProductGroupsDTO mapToProductGroupDTO(final ProductGroups productGroups){
        return new ProductGroupsDTO(
                productGroups.getId(), productGroups.getName(), productGroups.getDescription()
        );
    }
}

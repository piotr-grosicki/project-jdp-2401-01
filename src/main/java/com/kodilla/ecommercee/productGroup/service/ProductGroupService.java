package com.kodilla.ecommercee.productGroup.service;

import com.kodilla.ecommercee.productGroup.domain.ProductGroups;
import com.kodilla.ecommercee.productGroup.repository.ProductGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class ProductGroupService {
    private final ProductGroupRepository productGroupRepository;

    public List<ProductGroups> getAllProductGroups(){
        return productGroupRepository.findAll();
    }

    public Optional<ProductGroups> getProductGroupById(final Long productGroupId){
        return productGroupRepository.findById(productGroupId);
    }

    public ProductGroups createProductGroup(final ProductGroups productGroups){
        return productGroupRepository.save(productGroups);
    }

    public ProductGroups updateProductGroup(final ProductGroups productGroups){
        return productGroupRepository.save(productGroups);
    }

    public void deleteProductGroup(final Long productGroupId){
        productGroupRepository.deleteById(productGroupId);
    }
}

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

    public List<ProductGroups> getAllProductGroups() {
        return productGroupRepository.findAll();
    }

    public Optional<ProductGroups> getProductGroupById(Long id) {
        return productGroupRepository.findById(id);
    }

    public ProductGroups createProductGroup(ProductGroups productGroups) {
        return productGroupRepository.save(productGroups);
    }

    public ProductGroups updateProductGroup(ProductGroups productGroups) {
        return productGroupRepository.save(productGroups);
    }

    public void deleteProductGroup(Long id) {
        productGroupRepository.deleteById(id);
    }
}

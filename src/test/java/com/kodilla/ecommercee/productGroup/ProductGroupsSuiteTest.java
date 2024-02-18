package com.kodilla.ecommercee.productGroup;

import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.product.repository.ProductRepository;
import com.kodilla.ecommercee.productGroup.domain.ProductGroups;
import com.kodilla.ecommercee.productGroup.repository.ProductGroupRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProductGroupsSuiteTest {

    @Autowired
    private ProductGroupRepository productGroupRepository;

    @Test
    public void testSaveAndGetProductGroup(){
        //Given
        ProductGroups group = ProductGroups.builder()
                .name("Test Group")
                .description("Test Description")
                .build();

        //When
        ProductGroups savedGroup = productGroupRepository.save(group);
        Optional<ProductGroups> retrievedGroup = productGroupRepository.findById(savedGroup.getId());

        //Then
        assertTrue(retrievedGroup.isPresent());
        assertEquals("Test Group", retrievedGroup.get().getName());
        assertEquals("Test Description", retrievedGroup.get().getDescription());
    }

    @Test
    public void testUpdateProductGroup(){
        //Given
        ProductGroups group = ProductGroups.builder()
                .name("Test Group")
                .description("Test Description")
                .build();
        ProductGroups savedGroup = productGroupRepository.save(group);

        //When
        savedGroup.setName("Updated Name");
        savedGroup.setDescription("Updated Description");
        ProductGroups updatedGroup = productGroupRepository.save(savedGroup);
        Optional<ProductGroups> optionalGroup = productGroupRepository.findById(updatedGroup.getId());

        //Then
        assertEquals(savedGroup.getId(), optionalGroup.get().getId());
        assertEquals("Updated Name", optionalGroup.get().getName());
        assertEquals("Updated Description", optionalGroup.get().getDescription());
    }

    @Test
    public void testDeleteProductGroup(){
        //Given
        ProductGroups group = ProductGroups.builder()
                .name("Test Group")
                .description("Test Description")
                .build();
        ProductGroups savedGroup = productGroupRepository.save(group);

        //When
        productGroupRepository.deleteById(savedGroup.getId());
        Optional<ProductGroups> retrievedGroup = productGroupRepository.findById(savedGroup.getId());

        //Then
        assertFalse(retrievedGroup.isPresent());
    }

    @Test
    public void testGetAllProductGroups(){
        //Given
        ProductGroups group1 = ProductGroups.builder()
                .name("Test Group1")
                .description("Test Description1")
                .build();

        ProductGroups group2 = ProductGroups.builder()
                .name("Test Group2")
                .description("Test Description2")
                .build();

        productGroupRepository.save(group1);
        productGroupRepository.save(group2);

        //When
        List<ProductGroups> allGroups = productGroupRepository.findAll();

        //Then
        assertEquals(2, allGroups.size());
    }

    @Test
    public void testSaveProductWithProductGroup() {
        //Given
        ProductGroups group = ProductGroups.builder()
                .name("Test Group")
                .description("Test Description")
                .products(new ArrayList<>())
                .build();

        Product product = Product.builder()
                                .name("Headphones")
                                .price(new BigDecimal("129.99"))
                                .productGroups(group)
                                .build();

        group.getProducts().add(product);

        ProductGroups savedGroup = productGroupRepository.save(group);

        //When
        ProductGroups groupWithProducts = productGroupRepository.findById(savedGroup.getId()).orElse(null);

        //Then
        assertNotNull(groupWithProducts);
        assertEquals(1, groupWithProducts.getProducts().size());
        assertEquals("Headphones", groupWithProducts.getProducts().get(0).getName());
        assertEquals(new BigDecimal("129.99"), groupWithProducts.getProducts().get(0).getPrice());
    }
}
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

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductGroupsSuiteTest {

    @Autowired
    private ProductGroupRepository productGroupRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testSaveProductGroup(){
        //Given
        ProductGroups group = ProductGroups.builder()
                .name("Test Group")
                .description("Test Description")
                .build();

        //When
        assertNotNull(group, "Group is null before saving");

        ProductGroups savedGroup = productGroupRepository.save(group);
        Optional<ProductGroups> retrievedGroup = productGroupRepository.findById(savedGroup.getId());

        //Then
        assertTrue(retrievedGroup.isPresent());
        assertEquals("Test Group", retrievedGroup.get().getName());
        assertEquals("Test Description", retrievedGroup.get().getDescription());

        //CleanUp
        productGroupRepository.deleteById(savedGroup.getId());
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

        //Then
        assertEquals(savedGroup.getId(), updatedGroup.getId());
        assertEquals("Updated Name", updatedGroup.getName());
        assertEquals("Updated Description", updatedGroup.getDescription());

        //CleanUp
        productGroupRepository.deleteById(updatedGroup.getId());
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

        //CleanUp
        productGroupRepository.deleteAll();
    }
    @Test
    public void testSaveProductWithProductGroup() {
        //Given
        ProductGroups group = ProductGroups.builder()
                .name("Test Group")
                .description("Test Description")
                .build();

        ProductGroups savedGroup = productGroupRepository.save(group);

        Product product = new Product(1L, group, "Test Product Name", new BigDecimal(10), null);

        productRepository.save(product);

        //When
        ProductGroups groupWithProducts = productGroupRepository.findById(savedGroup.getId()).orElse(null);

        //Then
        assertNotNull(groupWithProducts);
        assertEquals(1, groupWithProducts.getProducts().size());
        assertEquals("Test Product Name", groupWithProducts.getProducts().get(0).getName());

        //CleanUp
        productGroupRepository.deleteAll();
        productRepository.deleteAll();
    }
}
package com.kodilla.ecommercee.product;

import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.cart.repository.CartRepository;
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
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductEntityTestSuite {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductGroupRepository productGroupRepository;
    @Autowired
    private CartRepository cartRepository;

    @Test
    public void testProductEntityRelations() {
        //Given
        ProductGroups testProductGroup = ProductGroups.builder()
                .name("Test Group").description("Testing builder").products(new ArrayList<>()).build();
        Product product1 = Product.builder()
                .name("test product1").price(new BigDecimal("11")).carts(new ArrayList<>()).build();
        Product product2 = Product.builder()
                .name("test product2").price(new BigDecimal("12")).carts(new ArrayList<>()).build();
        Product product3 = Product.builder()
                .name("test product3").price(new BigDecimal("13")).carts(new ArrayList<>()).build();
        Cart cart1 = Cart.builder().user(null).products(new ArrayList<>()).build();
        Cart cart2 = Cart.builder().user(null).products(new ArrayList<>()).build();

        product1.setProductGroups(testProductGroup);
        testProductGroup.getProducts().add(product1);
        testProductGroup.getProducts().add(product2);
        testProductGroup.getProducts().add(product3);
        cart1.getProducts().add(product1);
        cart1.getProducts().add(product2);
        cart1.getProducts().add(product3);
        cart2.getProducts().add(product3);
        cart2.getProducts().add(product3);
        cart2.getProducts().add(product2);
        product1.getCarts().add(cart1);
        product2.getCarts().add(cart1);
        product2.getCarts().add(cart2);
        product3.getCarts().add(cart1);
        product3.getCarts().add(cart2);

        //When
        productGroupRepository.save(testProductGroup);
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        cartRepository.save(cart1);
        cartRepository.save(cart2);

        //Then
        assertEquals(3, testProductGroup.getProducts().size());
        assertNotNull(product1.getProductId());
        assertNotNull(product2.getProductId());
        assertNotNull(product3.getProductId());
        assertEquals(cart1.getProducts().get(0).getProductId(), product1.getProductId());
        assertEquals(cart2.getProducts().get(2).getProductId(), product2.getProductId());
    }
}

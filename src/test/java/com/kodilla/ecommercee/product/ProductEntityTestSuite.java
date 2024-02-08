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
import java.util.List;
import java.util.Optional;

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
    public void getListOfProducts() {
        //Given
        Product product1 = Product.builder()
                .name("test product1").price(new BigDecimal("11")).carts(new ArrayList<>()).build();
        Product product2 = Product.builder()
                .name("test product2").price(new BigDecimal("12")).carts(new ArrayList<>()).build();
        Product product3 = Product.builder()
                .name("test product3").price(new BigDecimal("13")).carts(new ArrayList<>()).build();

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        //When
        List<Product> testList = productRepository.findAll();

        //Then
        assertEquals(3, testList.size());
        assertEquals("test product1", testList.get(0).getName());
        assertEquals(new BigDecimal("13.00"), testList.get(2).getPrice());

        //CleanUp
        productRepository.deleteAll();
    }

    @Test
    public void testCreateProduct() {
        //Given
        Product product1 = Product.builder()
                .name("test product1").price(new BigDecimal("11")).build();

        //When
        productRepository.save(product1);

        //Then
        assertNotEquals(0L, (long) product1.getProductId());

        //CleanUp
        productRepository.deleteAll();
    }

    @Test
    public void testGetProduct() {
        //Given
        Product product1 = Product.builder()
                .name("test product1").price(new BigDecimal("11")).build();
        productRepository.save(product1);

        //When
        Optional<Product> optionalProduct = productRepository.findById(product1.getProductId());

        //Then
        assertTrue(optionalProduct.isPresent());
        assertEquals("test product1", optionalProduct.map(Product::getName).orElse(null));

        //CleanUp
        productRepository.deleteAll();
    }

    @Test
    public void testDeletionOfProduct() {
        //Given
        Product product1 = Product.builder()
                .name("test product1").price(new BigDecimal("11")).build();
        productRepository.save(product1);

        //When
        productRepository.deleteById(product1.getProductId());
        Optional<Product> optionalProduct = productRepository.findById(product1.getProductId());

        //Then
        assertFalse(optionalProduct.isPresent());

        //CleanUp
        productRepository.deleteAll();
    }

    @Test
    public void testChangeProduct() {
        //Given
        Product product1 = Product.builder()
                .name("test product1").price(new BigDecimal("11")).build();
        productRepository.save(product1);

        //When
        product1.setPrice(new BigDecimal("55.99"));
        productRepository.save(product1);
        Optional<Product> optionalProduct = productRepository.findById(product1.getProductId());

        //Then
        assertEquals(new BigDecimal("55.99"), optionalProduct.map(Product::getPrice).orElse(null));

        //CleanUp
        productRepository.deleteAll();
    }

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
        assertNotEquals(0L, (long) product1.getProductId());
        assertNotEquals(0L, (long) testProductGroup.getId());
        assertNotEquals(0L, (long) cart1.getCartId());
        assertNotNull(productGroupRepository.findById(testProductGroup.getId()));
        assertNotNull(productRepository.findById(product2.getProductId()));
        assertNotNull(cartRepository.findById(cart2.getCartId()));

    }
}

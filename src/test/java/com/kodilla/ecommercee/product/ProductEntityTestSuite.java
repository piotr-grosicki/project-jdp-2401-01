package com.kodilla.ecommercee.product;

import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.cart.repository.CartRepository;
import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.product.repository.ProductRepository;
import com.kodilla.ecommercee.productGroup.domain.ProductGroups;
import com.kodilla.ecommercee.productGroup.repository.ProductGroupRepository;
import com.kodilla.ecommercee.user.domain.User;
import com.kodilla.ecommercee.user.domain.UserStatus;
import com.kodilla.ecommercee.user.repository.UserRepository;
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
public class ProductEntityTestSuite {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductGroupRepository productGroupRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void getListOfProducts() {
        //Given
        ProductGroups group = ProductGroups.builder().name("Test group")
                .description("Test description").products(new ArrayList<>()).build();
        Product product1 = Product.builder().name("test product1").price(new BigDecimal("11"))
                .carts(new ArrayList<>()).productGroups(group).build();
        Product product2 = Product.builder().name("test product2").price(new BigDecimal("12"))
                .carts(new ArrayList<>()).productGroups(group).build();
        Product product3 = Product.builder().name("test product3").price(new BigDecimal("13"))
                .carts(new ArrayList<>()).productGroups(group).build();

        group.getProducts().add(product1);
        group.getProducts().add(product2);
        group.getProducts().add(product3);

        productGroupRepository.save(group);

        //When
        List<Product> testList = productRepository.findAll();

        //Then
        assertEquals(3, testList.size());
        assertEquals("test product1", testList.get(0).getName());
        assertEquals(new BigDecimal("13"), testList.get(2).getPrice());
    }

    @Test
    public void testCreateAndGetProduct() {
        //Given
        ProductGroups group = ProductGroups.builder().name("Test group")
                .description("Test description").products(new ArrayList<>()).build();
        Product product1 = Product.builder().name("test product1").price(new BigDecimal("11"))
                .carts(new ArrayList<>()).productGroups(group).build();
        group.getProducts().add(product1);

        //When
        productGroupRepository.save(group);
        Optional<Product> optionalProduct = productRepository.findById(product1.getProductId());

        //Then
        assertNotEquals(0L, product1.getProductId());
        assertTrue(optionalProduct.isPresent());
        assertEquals("test product1", optionalProduct.map(Product::getName).orElse(null));
    }

    @Test
    public void testDeletionOfProduct() {
        //Given
        ProductGroups group = ProductGroups.builder().name("Test group")
                .description("Test description").products(new ArrayList<>()).build();
        Product product1 = Product.builder().name("test product1").price(new BigDecimal("11"))
                .carts(new ArrayList<>()).productGroups(group).build();
        group.getProducts().add(product1);
        productGroupRepository.save(group);

        //When
        productRepository.deleteById(product1.getProductId());
        Optional<Product> optionalProduct = productRepository.findById(product1.getProductId());

        //Then
        assertFalse(optionalProduct.isPresent());
    }

    @Test
    public void testChangeProduct() {
        //Given
        ProductGroups group = ProductGroups.builder().name("Test group")
                .description("Test description").products(new ArrayList<>()).build();
        Product product1 = Product.builder().name("test product1").price(new BigDecimal("11"))
                .carts(new ArrayList<>()).productGroups(group).build();
        group.getProducts().add(product1);
        productGroupRepository.save(group);

        //When
        product1.setPrice(new BigDecimal("55.99"));
        productRepository.save(product1);
        Optional<Product> optionalProduct = productRepository.findById(product1.getProductId());

        //Then
        assertEquals(new BigDecimal("55.99"), optionalProduct.map(Product::getPrice).orElse(null));
    }

    @Test
    public void testProductEntityRelations() {
        //Given
        ProductGroups testProductGroup = ProductGroups.builder()
                .name("Test Group").description("Testing builder").products(new ArrayList<>()).build();
        Product product1 = Product.builder().name("test product1").price(new BigDecimal("11"))
                .carts(new ArrayList<>()).productGroups(testProductGroup).build();
        Product product2 = Product.builder().name("test product2").price(new BigDecimal("12"))
                .carts(new ArrayList<>()).productGroups(testProductGroup).build();
        Product product3 = Product.builder().name("test product3").price(new BigDecimal("13"))
                .carts(new ArrayList<>()).productGroups(testProductGroup).build();
        testProductGroup.getProducts().add(product1);
        testProductGroup.getProducts().add(product2);
        testProductGroup.getProducts().add(product3);
        productGroupRepository.save(testProductGroup);

        List<Product> testList1 = new ArrayList<>();
        testList1.add(product1);
        testList1.add(product2);
        testList1.add(product3);
        List<Product> testList2 = new ArrayList<>();
        testList2.add(product3);
        testList2.add(product3);
        testList2.add(product2);

        User user = User.builder().username("Tim").password("12345").email("tim@gmail.com")
                .statusEnum(UserStatus.ACTIVE).carts(new ArrayList<>()).build();
        Cart cart1 = Cart.builder().user(user).products(testList1).build();
        Cart cart2 = Cart.builder().user(user).products(testList2).build();
        user.getCarts().add(cart1);
        user.getCarts().add(cart2);

        product1.getCarts().add(cart1);
        product2.getCarts().add(cart1);
        product2.getCarts().add(cart2);
        product3.getCarts().add(cart1);
        product3.getCarts().add(cart2);

        //When
        userRepository.save(user);

        Optional<Cart> optCart1 = cartRepository.findByCartId(cart1.getCartId());
        List<Product> prodList1 = optCart1.map(Cart::getProducts).orElse(null);

        //Then
        assertNotEquals(0L, product1.getProductId());
        assertNotEquals(0L, testProductGroup.getId());
        assertNotEquals(0L, cart1.getCartId());
        assertNotEquals(0L, cart2.getCartId());
        assertNotNull(productGroupRepository.findById(testProductGroup.getId()));
        assertNotNull(productRepository.findById(product2.getProductId()));
        assertNotNull(cartRepository.findByCartId(cart2.getCartId()));
        assert prodList1 != null;
        assertEquals(3, prodList1.size());
    }
}

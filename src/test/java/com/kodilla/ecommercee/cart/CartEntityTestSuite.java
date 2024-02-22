package com.kodilla.ecommercee.cart;

import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.cart.repository.CartRepository;
import com.kodilla.ecommercee.product.domain.Product;
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
public class CartEntityTestSuite {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductGroupRepository productGroupRepository;

    @Test
    public void testCreateAndGetCart() {
        //Given
        User user = User.builder().username("Tim").password("12345").email("tim@gmail.com")
                .statusEnum(UserStatus.ACTIVE).carts(new ArrayList<>()).build();
        Cart cart = Cart.builder().user(user).build();
        user.getCarts().add(cart);

        //When
        userRepository.save(user);
        Optional<Cart> optionalCart = cartRepository.findByCartId(cart.getCartId());

        //Then
        assertNotEquals(0L, optionalCart.map(Cart::getCartId).orElse(null));
        assertTrue(optionalCart.isPresent());
        assertEquals("Tim", optionalCart.map(u -> u.getUser().getUsername()).orElse(null));
    }

    @Test
    public void testUpdateCart() {
        //Given
        ProductGroups group = ProductGroups.builder().name("Test group").description("Test description")
                .products(new ArrayList<>()).build();
        Product product1 = Product.builder().name("test product1").price(new BigDecimal("11"))
                .carts(new ArrayList<>()).productGroups(group).build();
        Product product2 = Product.builder().name("test product2").price(new BigDecimal("12"))
                .carts(new ArrayList<>()).productGroups(group).build();
        group.getProducts().add(product1);
        group.getProducts().add(product2);
        productGroupRepository.save(group);

        User user = User.builder().username("Tim").password("12345").email("tim@gmail.com")
                .statusEnum(UserStatus.ACTIVE).carts(new ArrayList<>()).build();
        Cart cart = Cart.builder().user(user).products(new ArrayList<>()).build();
        user.getCarts().add(cart);

        cart.getProducts().add(product1);
        product1.getCarts().add(cart);
        userRepository.save(user);
        Optional<Cart> optionalCart = cartRepository.findByCartId(cart.getCartId());
        List<Product> prodList = optionalCart.map(Cart::getProducts).orElse(null);
        assert prodList != null;
        System.out.println("Product List size in Cart Entity before test: " + prodList.size());

        //When
        cart.getProducts().add(product2);
        product2.getCarts().add(cart);
        cartRepository.save(cart);
        Optional<Cart> optionalCart2 = cartRepository.findByCartId(cart.getCartId());
        List<Product> prodList2 = optionalCart2.map(Cart::getProducts).orElse(null);

        //Then
        assert prodList2 != null;
        assertEquals(2, prodList2.size());
        System.out.println("Product List size in Cart Entity after test: " + prodList2.size());
        assertEquals("test product1", cart.getProducts().get(0).getName());
        assertEquals(new BigDecimal("12"), cart.getProducts().get(1).getPrice());
    }

    @Test
    public void testDeleteCart() {
        //Given
        User user = User.builder().username("Tim").password("12345").email("tim@gmail.com")
                .statusEnum(UserStatus.ACTIVE).carts(new ArrayList<>()).build();
        Cart cart = Cart.builder().user(user).build();
        user.getCarts().add(cart);
        userRepository.save(user);

        //When
        user.getCarts().remove(cart);
        cartRepository.deleteById(cart.getCartId());
        Optional<Cart> optionalCart = cartRepository.findByCartId(cart.getCartId());

        //Then
        assertFalse(optionalCart.isPresent());
    }

    @Test
    public void testRemovingProductFromCart() {
        //Given
        ProductGroups group = ProductGroups.builder().name("Test group").description("Test description")
                .products(new ArrayList<>()).build();
        Product product1 = Product.builder().name("test product1").price(new BigDecimal("11"))
                .carts(new ArrayList<>()).productGroups(group).build();
        Product product2 = Product.builder().name("test product2").price(new BigDecimal("12"))
                .carts(new ArrayList<>()).productGroups(group).build();
        group.getProducts().add(product1);
        group.getProducts().add(product2);
        productGroupRepository.save(group);

        User user = User.builder().username("Tim").password("12345").email("tim@gmail.com")
                .statusEnum(UserStatus.ACTIVE).carts(new ArrayList<>()).build();
        Cart cart = Cart.builder().user(user).products(new ArrayList<>()).build();
        user.getCarts().add(cart);

        cart.getProducts().add(product1);
        cart.getProducts().add(product2);
        product1.getCarts().add(cart);
        product2.getCarts().add(cart);
        userRepository.save(user);
        Optional<Cart> optionalCart = cartRepository.findByCartId(cart.getCartId());
        List<Product> prodList = optionalCart.map(Cart::getProducts).orElse(null);
        assert prodList != null;
        System.out.println("Product List size in Cart Entity before test: " + prodList.size());

        //When
        cart.getProducts().remove(product2);
        product2.getCarts().remove(cart);
        cartRepository.save(cart);
        optionalCart = cartRepository.findByCartId(cart.getCartId());
        prodList = optionalCart.map(Cart::getProducts).orElse(null);

        //Then
        assert prodList != null;
        assertEquals(1, prodList.size());
        System.out.println("Product List size in Cart Entity after test: " + prodList.size());
        assertEquals("test product1", cart.getProducts().get(0).getName());
    }
}

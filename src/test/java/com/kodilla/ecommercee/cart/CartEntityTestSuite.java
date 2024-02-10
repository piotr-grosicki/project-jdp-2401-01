package com.kodilla.ecommercee.cart;

import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.cart.repository.CartRepository;
import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.product.repository.ProductRepository;
import com.kodilla.ecommercee.user.domain.User;
import com.kodilla.ecommercee.user.domain.UserStatus;
import com.kodilla.ecommercee.user.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartEntityTestSuite {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateCart() {
        //Given
        Cart cart1 = Cart.builder().build();

        //When
        cartRepository.save(cart1);

        //Then
        assertNotEquals(0L, cart1.getCartId());
        assertTrue(cartRepository.findById(cart1.getCartId()).isPresent());

        //CleanUp
        cartRepository.deleteAll();
    }

    @Test
    public void testGetCart() {
        //Given
        Cart cart1 = Cart.builder().products(new ArrayList<>()).build();
        Product product1 = Product.builder()
                .name("Test product 1").price(new BigDecimal("14.99")).carts(new ArrayList<>()).build();
        Product product2 = Product.builder()
                .name("Test product 2").price(new BigDecimal("19.99")).carts(new ArrayList<>()).build();
        User user1 = User.builder()
                .username("Alex").password("12345").carts(new ArrayList<>()).statusEnum(UserStatus.ACTIVE).build();

        cart1.getProducts().add(product1);
        cart1.getProducts().add(product2);
        cart1.setUser(user1);
        product1.getCarts().add(cart1);
        product2.getCarts().add(cart1);
        user1.getCarts().add(cart1);

        userRepository.save(user1);
        cartRepository.save(cart1);
        productRepository.save(product1);
        productRepository.save(product2);

        //When
        Optional<Cart> optionalCart = cartRepository.findByCartId(cart1.getCartId());
        List<Product> prodList = optionalCart.map(Cart::getProducts).orElse(null);

        //Then
        assertTrue(optionalCart.isPresent());
        assertEquals("Alex", optionalCart.map(u -> u.getUser().getUsername()).orElse(null));
        assertEquals(2, prodList.size());
        assertEquals(new BigDecimal("19.99"), prodList.get(1).getPrice());

        //CleanUp
        cartRepository.deleteAll();
        userRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    public void testUpdateCart() {
        //Given
        Cart cart1 = Cart.builder().products(new ArrayList<>()).build();
        Product product1 = Product.builder()
                .name("Test product 1").price(new BigDecimal("14.99")).carts(new ArrayList<>()).build();
        Product product2 = Product.builder()
                .name("Test product 2").price(new BigDecimal("19.99")).carts(new ArrayList<>()).build();

        cart1.getProducts().add(product1);
        product1.getCarts().add(cart1);
        cartRepository.save(cart1);
        Optional<Cart> optionalCart = cartRepository.findByCartId(cart1.getCartId());
        List<Product> prodList = optionalCart.map(Cart::getProducts).orElse(null);
        assert prodList != null;
        System.out.println("Product List size in Cart Entity before test: " + prodList.size());

        //When
        cart1.getProducts().add(product2);
        product2.getCarts().add(cart1);
        cartRepository.save(cart1);
        Optional<Cart> optionalCart2 = cartRepository.findByCartId(cart1.getCartId());
        List<Product> prodList2 = optionalCart2.map(Cart::getProducts).orElse(null);

        //Then
        assert prodList2 != null;
        assertEquals(2, prodList2.size());
        System.out.println("Product List size in Cart Entity after test: " + prodList2.size());

        //CleanUp
        cartRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    public void testDeleteCart() {
        //Given
        Cart cart1 = Cart.builder().build();
        cartRepository.save(cart1);

        //When
        cartRepository.deleteById(cart1.getCartId());
        Optional<Cart> optionalCart = cartRepository.findByCartId(cart1.getCartId());

        //Then
        assertFalse(optionalCart.isPresent());
    }

    @Test
    public void testRemovingProductFromCart() {
        //Given
        Cart cart1 = Cart.builder().products(new ArrayList<>()).build();
        Product product1 = Product.builder()
                .name("Test product 1").price(new BigDecimal("14.99")).carts(new ArrayList<>()).build();
        Product product2 = Product.builder()
                .name("Test product 2").price(new BigDecimal("19.99")).carts(new ArrayList<>()).build();

        cart1.getProducts().add(product1);
        cart1.getProducts().add(product2);
        product1.getCarts().add(cart1);
        product2.getCarts().add(cart1);
        cartRepository.save(cart1);
        Optional<Cart> optionalCart = cartRepository.findByCartId(cart1.getCartId());
        List<Product> prodList = optionalCart.map(Cart::getProducts).orElse(null);
        assert prodList != null;
        System.out.println("Product List size in Cart Entity before test: " + prodList.size());

        //When
        cart1.getProducts().remove(product2);
        product2.getCarts().remove(cart1);
        cartRepository.save(cart1);
        optionalCart = cartRepository.findByCartId(cart1.getCartId());
        prodList = optionalCart.map(Cart::getProducts).orElse(null);

        //Then
        assert prodList != null;
        assertEquals(1, prodList.size());
        System.out.println("Product List size in Cart Entity after test: " + prodList.size());

        //CleanUp
        cartRepository.deleteAll();
        productRepository.deleteAll();
    }
}

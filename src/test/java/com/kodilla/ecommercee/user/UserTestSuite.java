package com.kodilla.ecommercee.user;

import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.order.domain.Order;
import com.kodilla.ecommercee.user.domain.User;
import com.kodilla.ecommercee.user.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class UserTestSuite {

    @Autowired
    public UserRepository userRepository;

    @Test
    public void testSaveUser() {
        //Given
        User user = User.builder()
                .username("username")
                .password("password")
                .build();

        //When
        User savedUser = userRepository.save(user);
        Optional<User> retrievedUser = userRepository.findById(savedUser.getUserId());

        //Then
        assertTrue(retrievedUser.isPresent());
        assertEquals("username", retrievedUser.get().getUsername());
        assertEquals("password", retrievedUser.get().getPassword());

        //CleanUp
        userRepository.deleteAll();
    }

    @Test
    public void testUpdateUser() {
        //Given
        User user = User.builder()
                .username("username")
                .password("password")
                .build();

        User savedUser = userRepository.save(user);

        //When
        user.setUsername("updated username");
        user.setPassword("updated password");
        userRepository.save(user);
        Optional<User> updatedUser = userRepository.findById(user.getUserId());

        //Then
        assertEquals("updated username", updatedUser.get().getUsername());
        assertEquals("updated password", updatedUser.get().getPassword());

        //CleanUp
        userRepository.deleteAll();
    }

    @Test
    public void testDeleteUser() {
        //Given
        User user = User.builder()
                .username("username")
                .password("password")
                .build();
        userRepository.save(user);

        //When
        userRepository.deleteById(user.getUserId());
        Optional<User> deletedUser = userRepository.findById(user.getUserId());

        //Then
        assertFalse(deletedUser.isPresent());
    }

    @Test
    public void testSaveUserWithCart() {
        //Given
        User user = User.builder()
                .username("username")
                .password("password")
                .carts(new ArrayList<>())
                .build();

        Cart cart = new Cart();
        cart.setUser(user);
        user.getCarts().add(cart);

        //When
        userRepository.save(user);
        Optional<User> savedUser = userRepository.findById(user.getUserId());

        //Then
        assertTrue(savedUser.isPresent());
        assertNotNull(savedUser.get());
        assertFalse(savedUser.get().getCarts().isEmpty());

        //CleanUp
        userRepository.deleteAll();
    }

    @Test
    public void testSaveUserWithOrder() {
        //Given
        User user = User.builder()
                .username("username")
                .password("password")
                .orders(new ArrayList<>())
                .build();

        Order order = new Order();
        order.setUser(user);
        user.getOrders().add(order);

        //When
        userRepository.save(user);
        Optional<User> savedUser = userRepository.findById(user.getUserId());

        //Then
        assertTrue(savedUser.isPresent());
        assertNotNull(savedUser.get());
        assertFalse(savedUser.get().getOrders().isEmpty());

        //CleanUp
        userRepository.deleteAll();
    }
}

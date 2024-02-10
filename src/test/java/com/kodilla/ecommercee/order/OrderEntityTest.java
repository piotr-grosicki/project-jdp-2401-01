package com.kodilla.ecommercee.order;

import com.kodilla.ecommercee.cart.Domain.Cart;
import com.kodilla.ecommercee.cart.repository.CartRepository;
import com.kodilla.ecommercee.order.domain.Order;
import com.kodilla.ecommercee.order.repository.OrderRepository;
import com.kodilla.ecommercee.user.domain.User;
import com.kodilla.ecommercee.user.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderEntityTest {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;

    @Test
    public void createOrderTest() {
        //Given
        Order order = Order.builder()
                .orderValue(new BigDecimal(15.67)).build();
        //When
        orderRepository.save(order);
        long id = order.getOrderId();
        System.out.println(id);
        //Then
        assertTrue(orderRepository.existsById(order.getOrderId()));
        //Cleanup
        orderRepository.deleteById(order.getOrderId());
    }
    @Test
    public void readOrderTest() {
        //Given
        Order order = Order.builder()
                .orderValue(new BigDecimal(15.67)).build();
        //When
        orderRepository.save(order);
        List<Order> orders = orderRepository.findAll();
        //Then
        assertEquals(1, orders.size());
        //Cleanup
        orderRepository.deleteById(order.getOrderId());
    }
    @Test
    public void deleteOrderTest(){
        //Given
        User user = User.builder()
                .build();
        Cart cart = Cart.builder()
                .build();
        Order order = Order.builder()
                .orderValue(new BigDecimal(15.67)).build();
        //When
        userRepository.save(user);
        orderRepository.save(order);
        orderRepository.deleteById(order.getOrderId());
        List<Order> orders = orderRepository.findAll();
        //Then
        assertEquals(0,orders.size());
        //Cleanup
        userRepository.deleteById(user.getUserId());
    }
    @Test
    public void updateOrderTest(){
        //Given
        Order order = Order.builder()
                .orderValue(new BigDecimal(15.67)).build();
        //When
        orderRepository.save(order);
        order.setOrderValue(new BigDecimal(15.78));
        //Then
        assertEquals(new BigDecimal(15.78),order.getOrderValue());
        //Cleanup
        orderRepository.deleteAll();
    }
}

package com.kodilla.ecommercee.order.service;

import com.kodilla.ecommercee.order.controller.OrderNotFoundException;
import com.kodilla.ecommercee.order.domain.Order;
import com.kodilla.ecommercee.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<Order> getAllOrders(){return orderRepository.findAll();}
    public Order getOrder(final long orderId) throws OrderNotFoundException {
        return orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
    }

    public Order saveOrder(final Order order){
        return orderRepository.save(order);
    }

    public void deleteOrder(final long orderId){
        orderRepository.deleteById(orderId);
    }
}

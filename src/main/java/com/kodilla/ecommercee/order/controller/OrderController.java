package com.kodilla.ecommercee.order.controller;

import com.kodilla.ecommercee.order.domain.Order;
import com.kodilla.ecommercee.order.domain.OrderDto;
import com.kodilla.ecommercee.order.mapper.OrderMapper;
import com.kodilla.ecommercee.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrders(){
        List<Order> orderList = orderService.getAllOrders();
        return ResponseEntity.ok(orderMapper.mapToOrderDtoList(orderList));
    }
    @GetMapping(value = "{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable long orderId) throws OrderNotFoundException{
        return ResponseEntity.ok(orderMapper.mapToOrderDto(orderService.getOrder(orderId)));
    }
    @DeleteMapping(value = "{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable long orderId){
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
    @PutMapping
    public ResponseEntity<OrderDto> updateOrder(@RequestBody OrderDto orderDto){
        Order order = orderMapper.mapToOrder(orderDto);
        Order savedOrder = orderService.saveOrder(order);
        return ResponseEntity.ok(orderMapper.mapToOrderDto(savedOrder));
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createOrder(@RequestBody OrderDto orderDto){
        Order order = orderMapper.mapToOrder(orderDto);
        orderService.saveOrder(order);
        return ResponseEntity.ok().build();
    }
}

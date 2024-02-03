package com.kodilla.ecommercee.order.controller;

import com.kodilla.ecommercee.order.domain.OrderDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @GetMapping
    public List<OrderDto> getOrders(){
        List<OrderDto> orders = new ArrayList<>();
        orders.add(new OrderDto(1,"order1"));
        orders.add(new OrderDto(2,"order2"));
        orders.add(new OrderDto(3,"order3"));
        return orders;
    }
    @GetMapping(value = "{orderId}")
    public OrderDto getOrder(@PathVariable int orderId){
        return new OrderDto(1,"user");
    }
    @DeleteMapping(value = "{orderId}")
    public void deleteOrder(@PathVariable Long orderId){
        System.out.println("Delete order");
    }
    @PutMapping
    public OrderDto updateOrder(@RequestBody OrderDto orderDto){
        return new OrderDto(4,"updated order");
    }
    @PostMapping
    public void createOrder(@RequestBody OrderDto orderDTO){
        System.out.println("Create order");
    }
}

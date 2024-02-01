package com.kodilla.ecommercee.order.controller;

import com.kodilla.ecommercee.order.domain.OrderDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @GetMapping
    public List<OrderDTO> getOrders(){
        List<OrderDTO> orders = new ArrayList<>();
        orders.add(new OrderDTO(1,"order1"));
        orders.add(new OrderDTO(2,"order2"));
        orders.add(new OrderDTO(3,"order3"));
        return orders;
    }
    @GetMapping(value = "{orderId}")
    public OrderDTO getOrder(@PathVariable int orderId){
        return new OrderDTO(1,"user");
    }
    @DeleteMapping(value = "{orderId}")
    public void deleteOrder(@PathVariable Long orderId){
        System.out.println("Delete order");
    }
    @PutMapping
    public OrderDTO updateOrder(){
        return new OrderDTO(4,"updated order");
    }
    @PostMapping
    public void createOrder(@RequestBody OrderDTO orderDTO){
        System.out.println("Create order");
    }
}

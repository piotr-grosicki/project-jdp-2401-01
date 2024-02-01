package com.kodilla.ecommercee.Order;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @GetMapping
    public List<OrderTestDTO> getOrders(){
        List<OrderTestDTO> orders = new ArrayList<>();
        orders.add(new OrderTestDTO(1,"order1"));
        orders.add(new OrderTestDTO(2,"order2"));
        orders.add(new OrderTestDTO(3,"order3"));
        return orders;
    }
    @GetMapping(value = "{orderId}")
    public OrderTestDTO getOrder(@PathVariable int orderId){
        return new OrderTestDTO(1,"user");
    }
    @DeleteMapping(value = "{orderId}")
    public void deleteOrder(@PathVariable Long orderId){
        System.out.println("Delete order");
    }
    @PutMapping
    public OrderTestDTO updateOrder(){
        return new OrderTestDTO(4,"updated order");
    }
    @PostMapping
    public void createOrder(@RequestBody OrderTestDTO orderTestDTO){
        System.out.println("Create order");
    }
}

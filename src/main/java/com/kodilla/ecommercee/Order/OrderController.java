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
        List<OrderTestDTO> orders = new ArrayList<>();
        orders.add(new OrderTestDTO(1,"order1"));
        orders.add(new OrderTestDTO(2,"order2"));
        orders.add(new OrderTestDTO(3,"order3"));
        return orders.get(orderId-1);
    }
    @DeleteMapping(value = "{orderId}")
    public String deleteOrder(@PathVariable Long orderId){
        return ("task " +orderId + " deleted");
    }
    @PutMapping
    public OrderTestDTO updateOrder(){
        return new OrderTestDTO(4,"updated order");
    }
    @PostMapping
    public String createOrder(){
        return "order created";
    }

}

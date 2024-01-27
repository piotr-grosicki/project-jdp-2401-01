package com.kodilla.ecommercee.Order;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @GetMapping
    public List<String> getOrders(){
        return new ArrayList<>();
    }
    @GetMapping(value = "{orderId}")
    public String getOrder(@PathVariable Long orderId){
        return "order " + orderId;
    }
    @DeleteMapping(value = "{orderId}")
    public String deleteOrder(@PathVariable Long orderId){
        return "task " +orderId + " deleted";
    }
    @PutMapping
    public String updateOrder(){
        return "order update";
    }
    @PostMapping
    public String createOrder(){
        return "order created";
    }

}

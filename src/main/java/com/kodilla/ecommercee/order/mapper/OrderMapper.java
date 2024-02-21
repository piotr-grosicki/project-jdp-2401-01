package com.kodilla.ecommercee.order.mapper;

import com.kodilla.ecommercee.order.domain.Order;
import com.kodilla.ecommercee.order.domain.OrderDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMapper {
    public Order mapToOrder(OrderDto orderDto) {
        return new Order(
                orderDto.getOrderId(),
                orderDto.getCart(),
                orderDto.getUser(),
                orderDto.getValue());
    }

    public OrderDto mapToOrderDto(Order order) {
        return new OrderDto(order.getOrderId(),
                order.getUser(),
                order.getCart(),
                order.getOrderValue());
    }

    public List<OrderDto> mapToOrderDtoList (List<Order> orderList) {
        return orderList.stream()
                .map(this::mapToOrderDto)
                .collect(Collectors.toList());
    }
}

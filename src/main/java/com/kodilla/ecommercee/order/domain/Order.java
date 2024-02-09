package com.kodilla.ecommercee.order.domain;

import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Long orderId;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CART_ID")
    private Cart cart;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    private BigDecimal orderValue;
}

package com.kodilla.ecommercee.user.domain;

import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.order.domain.Order;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "USERS")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "USER_ID", unique = true)
    private Long userId;
    @Column(name = "USERNAME", unique = true)
    private String username;
    @Column(name = "PASSWORD", unique = true)
    private String password;
    @Column(name = "EMAIL", unique = true)
    private String email;
    @Column(name ="STATUS")
    private Enum<UserStatus> statusEnum;
    @Column(name = "KEY_EXPIRATION_TIME")
    private LocalDateTime keyExpirationTime;
    @Column(name = "GENERATED_KEY")
    private String generatedKey;
    @OneToMany(
            targetEntity = Cart.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Cart> carts = new ArrayList<>();
    @OneToMany(
            targetEntity = Order.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Order> orders = new ArrayList<>();
}

package com.kodilla.ecommercee.user.domain;

import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.order.domain.Order;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", unique = true)
    private Long userId;
    @NotNull
    @Column(name = "USERNAME", unique = true)
    private String username;
    @NotNull
    @Column(name = "PASSWORD", unique = true)
    private String password;
    @NotNull
    @Column(name = "EMAIL", unique = true)
    private String email;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name ="STATUS")
    private UserStatus statusEnum;
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

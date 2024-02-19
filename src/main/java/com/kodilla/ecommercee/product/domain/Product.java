package com.kodilla.ecommercee.product.domain;

import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.productGroup.domain.ProductGroups;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "PRODUCTS")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID", unique = true)
    private Long productId;
    @ManyToOne
    @JoinColumn(name = "PRODUCT_GROUP_ID")
    @NotNull
    private ProductGroups productGroups;
    @Column(name = "NAME")
    @NotNull
    private String name;
    @Column(name = "PRICE")
    @NotNull
    private BigDecimal price;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "products", fetch = FetchType.EAGER)
    private List<Cart> carts = new ArrayList<>();
}

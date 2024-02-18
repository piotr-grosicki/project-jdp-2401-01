package com.kodilla.ecommercee.productGroup.domain;

import com.kodilla.ecommercee.product.domain.Product;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "PRODUCT_GROUPS")
public class ProductGroups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_GROUP_ID", unique = true)
    private Long id;
    @NotNull
    @Column(name = "NAME")
    private String name;
    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;
    @OneToMany(
            targetEntity = Product.class,
            mappedBy = "productGroups",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER
    )
    private List<Product> products =new ArrayList<>();
}

package com.kodilla.ecommercee.productGroup.domain;

import com.kodilla.ecommercee.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "PRODUCT_GROUPS")
public class ProductGroups {
    @Id
    @GeneratedValue
    @Column(name = "PRODUCT_GROUP_ID", unique = true)
    private Long id;

    @Column(name = "PRODUCT_GROUP_NAME")
    private String name;

    @Column(name = "PRODUCT_GROUP_DESCRIPTION")
    private String description;

    @OneToMany(
            targetEntity = Product.class,
            mappedBy = "productGroups",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Product> products =new ArrayList<>();
}

package com.kodilla.ecommercee.productGroup.domain;

import com.kodilla.ecommercee.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODCT_GROPS")
public class ProductGroups {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "PRODUCT_GROUP_ID", unique = true)
    private Long productGroupId;

    @NotNull
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
    private List<Product> productList = new ArrayList<>();
}

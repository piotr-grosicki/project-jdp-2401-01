package com.kodilla.ecommercee.productGroup.repository;

import com.kodilla.ecommercee.productGroup.domain.ProductGroups;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface ProductGroupRepository extends CrudRepository<ProductGroups, Long> {

    @Override
    List<ProductGroups> findAll();

    @Override
    Optional<ProductGroups> findById(Long productGroupId);

    @Override
    ProductGroups save(ProductGroups productGroups);
}

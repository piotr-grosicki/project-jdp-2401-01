package com.kodilla.ecommercee.productGroup.repository;

import com.kodilla.ecommercee.productGroup.domain.ProductGroups;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface ProductGroupRepository extends CrudRepository<ProductGroups, Long> {
}

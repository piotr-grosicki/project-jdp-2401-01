package com.kodilla.ecommercee.product.repository;

import com.kodilla.ecommercee.product.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    @Override
    List<Product> findAll();

    Optional<Product> findById(Long id);

    @Override
    Product save(Product product);
}

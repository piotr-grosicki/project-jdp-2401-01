package com.kodilla.ecommercee.cart.repository;

import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.product.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {
    List<Product> findAllByCartId(Long cartId);
    @Override
    List<Cart> findAll();
    @Override
    Cart save(Cart cart);
}

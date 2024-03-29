package com.kodilla.ecommercee.user.repository;

import com.kodilla.ecommercee.user.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    User save(User user);

    User findByUserId(Long userId);
}

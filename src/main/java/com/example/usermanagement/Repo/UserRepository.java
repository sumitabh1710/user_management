package com.example.usermanagement.Repo;

import org.springframework.data.repository.CrudRepository;

import com.example.usermanagement.Entity.User;


public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);

    boolean existsByEmail(String email);

    User getById(Long id);
    
}
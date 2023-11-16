package com.yasin.blogapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yasin.blogapp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Additional custom query methods (if needed) can be defined here
}


package com.yasin.blogapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yasin.blogapp.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    List<Comment> findAllByOrderByCreatedAtAsc();

}

package com.yasin.blogapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.yasin.blogapp.entity.BlogPost;
import com.yasin.blogapp.entity.User;
import com.yasin.blogapp.services.IBlogPostService;
import com.yasin.blogapp.services.IUserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/blogposts")
public class BlogPostController {

    private final IBlogPostService blogPostService;

    @Autowired
    public BlogPostController(IBlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @GetMapping
    public ResponseEntity<List<BlogPost>> getAllBlogPosts() {
        List<BlogPost> blogPosts = blogPostService.getAllBlogPosts();
        return new ResponseEntity<>(blogPosts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogPost> getBlogPostById(@PathVariable Long id) {
        BlogPost blogPost = blogPostService.getBlogPostById(id);
        return blogPost != null ? new ResponseEntity<>(blogPost, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<BlogPost> createBlogPost(@Valid @RequestBody BlogPost blogPost) {
        BlogPost createdBlogPost = blogPostService.createBlogPost(blogPost);
        return new ResponseEntity<>(createdBlogPost, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogPost> updateBlogPost(@Valid @RequestBody BlogPost blogPost) {
        BlogPost updatedBlogPost = blogPostService.updateBlogPost(blogPost);
        return updatedBlogPost != null ? new ResponseEntity<>(updatedBlogPost, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlogPost(@PathVariable Long id) {
        blogPostService.deleteBlogPostById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}


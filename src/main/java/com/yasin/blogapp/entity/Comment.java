package com.yasin.blogapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;import java.time.LocalDateTime;

@Entity
@Table(name = "Comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentID;

    private String text;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "postID")
    private BlogPost blogPost;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;
}

package com.yasin.blogapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "Comment")
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentID;

    @NotBlank(message = "Commet is required")
    private String text;
    
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "postID")
    private BlogPost blogPost;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;
}

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
@Table(name = "BlogPost")
@Data
public class BlogPost {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long postID;

	@NotBlank(message = "Title is required")
	@Size(min = 5, max = 100, message = "Title must be between 5 and 100 characters")
	private String title;

	@NotBlank(message = "Blog content is required")
	@Size(min = 5, max = 20000, message = "Content must be between 5 and 100 characters")
	private String content;

	private String imageURL;

	@Size(max = 255, message = "Meta title must not exceed 255 characters")
	private String metaTitle;

	@Size(max = 500, message = "Meta description must not exceed 500 characters")
	private String metaDescription;

	private String permalink;

	@Size(max = 100, message = "Image alt text must not exceed 100 characters")
	private String imageAltText;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	@ManyToOne
	@JoinColumn(name = "categoryID")
	private Category category;

	@ManyToOne
	@JoinColumn(name = "userID")
	private User user;

}

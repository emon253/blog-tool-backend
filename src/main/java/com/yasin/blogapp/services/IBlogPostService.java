package com.yasin.blogapp.services;

import java.util.List;

import com.yasin.blogapp.entity.BlogPost;

public interface IBlogPostService {
	List<BlogPost> getAllBlogPosts();

    BlogPost getBlogPostById(Long id);

    BlogPost createBlogPost(BlogPost blogPost);

    BlogPost updateBlogPost(BlogPost blogPost);

    void deleteBlogPostById(Long id);
}

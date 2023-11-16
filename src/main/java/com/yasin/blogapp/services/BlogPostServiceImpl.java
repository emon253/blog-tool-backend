package com.yasin.blogapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.yasin.blogapp.entity.BlogPost;
import com.yasin.blogapp.entity.Category;
import com.yasin.blogapp.entity.User;
import com.yasin.blogapp.exceptions.ResourceNotFoundException;
import com.yasin.blogapp.repositories.BlogPostRepository;
import com.yasin.blogapp.repositories.CategoryRepository;
import com.yasin.blogapp.repositories.UserRepository;

public class BlogPostServiceImpl implements IBlogPostService {

	@Autowired
	private BlogPostRepository blogRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<BlogPost> getAllBlogPosts() {

		return this.blogRepository.findAll();

	}

	@Override
	public BlogPost getBlogPostById(Long id) {
		return blogRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Blog", "blogid"));
	}

	@Override
	@Transactional
	public BlogPost createBlogPost(BlogPost blogPost) {
		setCategoryAndUser(blogPost);
		return blogRepository.save(blogPost);
	}

	@Override
	@Transactional
	public BlogPost updateBlogPost(BlogPost blogPost) {
		setCategoryAndUser(blogPost);
		return blogRepository.save(blogPost);
	}

	@Override
	@Transactional
	public void deleteBlogPostById(Long id) {
		BlogPost blogPost = blogRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Blog", "blogid"));
		blogRepository.delete(blogPost);
	}

	// Helper method to set category and user
	private void setCategoryAndUser(BlogPost blogPost) {
		Category category = categoryRepository.findById(blogPost.getCategory().getCategoryID())
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id"));
		User user = userRepository.findById(blogPost.getUser().getUserID())
				.orElseThrow(() -> new ResourceNotFoundException("User", "user id"));

		blogPost.setCategory(category);
		blogPost.setUser(user);
	}

}

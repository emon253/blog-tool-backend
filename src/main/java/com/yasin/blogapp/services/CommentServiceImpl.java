package com.yasin.blogapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yasin.blogapp.entity.BlogPost;
import com.yasin.blogapp.entity.Comment;
import com.yasin.blogapp.entity.User;
import com.yasin.blogapp.exceptions.ResourceNotFoundException;
import com.yasin.blogapp.repositories.BlogPostRepository;
import com.yasin.blogapp.repositories.CategoryRepository;
import com.yasin.blogapp.repositories.CommentRepository;
import com.yasin.blogapp.repositories.UserRepository;

@Service
public class CommentServiceImpl implements ICommentService {

	@Autowired
	private IBlogPostService blogService;

	@Autowired
	private IUserService userService;

	@Autowired
	private CommentRepository commentRepository;

	@Override
	public List<Comment> getAllComments() {
		return this.commentRepository.findAllByOrderByCreatedAtAsc();
	}

	@Override
	public Comment getCommentById(Long id) {
		return this.commentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "commentid"));
	}

	@Override
	public Comment createComment(Comment comment) {
		setUserAndBlogPostIntoCommnet(comment);
		return this.commentRepository.save(comment);
	}

	@Override
	public Comment updateComment(Comment comment) {
		setUserAndBlogPostIntoCommnet(comment);
		return this.commentRepository.save(comment);
	}

	@Override
	public void deleteCommentById(Long id) {
		Comment comment = this.getCommentById(id);
		
		this.commentRepository.delete(comment);
	}

	private void setUserAndBlogPostIntoCommnet(Comment comment) {
		BlogPost blogPost = this.blogService.getBlogPostById(comment.getBlogPost().getPostID());

		User user = this.userService.getUserById(comment.getUser().getUserID());

		comment.setBlogPost(blogPost);

		comment.setUser(user);
	}
}

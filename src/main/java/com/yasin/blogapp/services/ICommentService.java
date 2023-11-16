package com.yasin.blogapp.services;

import java.util.List;

import com.yasin.blogapp.entity.Comment;

public interface ICommentService {
	 List<Comment> getAllComments();

	    Comment getCommentById(Long id);

	    Comment createComment(Comment comment);

	    Comment updateComment(Comment comment);

	    void deleteCommentById(Long id);
}

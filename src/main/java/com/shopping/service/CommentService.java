package com.shopping.service;

import java.util.List;

import com.shopping.entity.Comment;

public interface CommentService {

	List<Comment> getAllComment();

	void addComment(Comment comment);

	boolean deleteComment(int id);

	Comment getComment(int id);

}

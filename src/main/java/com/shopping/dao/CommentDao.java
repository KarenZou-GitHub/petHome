package com.shopping.dao;

import java.util.List;

import com.shopping.entity.Comment;

public interface CommentDao {

	List<Comment> getAllComment();

	void addComment(Comment comment);

	boolean deleteComment(int id);

	Comment getComment(int id);

}

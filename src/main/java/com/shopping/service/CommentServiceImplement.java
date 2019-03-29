package com.shopping.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.dao.CommentDao;
import com.shopping.entity.Comment;

@Service
public class CommentServiceImplement implements CommentService {

	@Autowired
    private CommentDao commentdao;
	@Override
	public List<Comment> getAllComment() {
		return commentdao.getAllComment();
	}

	@Override
	public void addComment(Comment comment) {
		commentdao.addComment(comment);
	}

	@Override
	public boolean deleteComment(int id) {
		
		return commentdao.deleteComment(id);
	}

	@Override
	public Comment getComment(int id) {
		return commentdao.getComment(id);
	}

}

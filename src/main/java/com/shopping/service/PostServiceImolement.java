package com.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.dao.PostDao;
import com.shopping.entity.Post;
@Service
public class PostServiceImolement implements PostService {

	@Autowired
	private PostDao postdao;
	@Override
	public List<Post> getAllPosts() {
		return postdao.getAllPosts();
	}

	@Override
	public void addPost(Post post) {
		postdao.addPost(post);
	}

	@Override
	public boolean deletePost(int id) {
		return postdao.deletePost(id);
	}

	@Override
	public Post getPost(int id) {
		return postdao.getPost(id);
	}

	@Override
	public boolean updatePost(Post post) {
		return postdao.updatePost(post);
	}

	@Override
	public Post getPost(String name) {
		return postdao.getPost(name);
	}

}

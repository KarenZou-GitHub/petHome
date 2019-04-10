package com.shopping.dao;

import java.util.List;

import com.shopping.entity.Post;

public interface PostDao {

	List<Post> getAllPosts();

	void addPost(Post post);

	boolean deletePost(int id);

	Post getPost(int id);
	
	Post getPost(String name);

	List<Post> getPostByUser(int userId);

	boolean updatePost(Post post);

}

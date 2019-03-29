package com.shopping.service;

import java.util.List;
import com.shopping.entity.Post;

public interface PostService {

	List<Post> getAllPosts();

	void addPost(Post post);

	boolean deletePost(int id);

	Post getPost(int id);
	
	Post getPost(String name);
	
    boolean updatePost(Post post);

}

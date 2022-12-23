package com.ePocket.ws.post;

import java.util.Date;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.ePocket.ws.user.User;
import com.ePocket.ws.user.UserService;

@Service
public class PostService {
	PostRepository postRepository;
	UserService userService;

	public PostService(PostRepository postRepository, UserService userService) {
		
		this.postRepository = postRepository;
		this.userService = userService;
	}

	public void save(Post post, User user) {
		post.setTimeStampDate(new Date());
		post.setUser(user);
		postRepository.save(post);
		
	}

	public Page<Post> getPosts(Pageable page) {
		// TODO Auto-generated method stub
		return postRepository.findAll(page);
	}
	
	public Page<Post> getHoaxesOfUser(String username, Pageable page) {
		User inDB = userService.getByUsername(username);
		return postRepository.findByUser(inDB, page);
	}
	

	
	
	
	
}

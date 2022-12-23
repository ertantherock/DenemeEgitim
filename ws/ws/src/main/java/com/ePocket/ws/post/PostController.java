package com.ePocket.ws.post;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ePocket.ws.post.vm.PostVM;
import com.ePocket.ws.shared.ResponseGeneric;
import com.ePocket.ws.shared.UserCurrent;
import com.ePocket.ws.user.User;

@RestController
public class PostController {
	
	@Autowired
	PostService postService;
	
	@PostMapping("/api/1.0/posts")
	ResponseGeneric savePost(@Valid @RequestBody Post post, @UserCurrent User user) {
		postService.save(post, user);
		return new ResponseGeneric("Post Succesfully Saved");

	}
	@GetMapping("/api/1.0/posts")
	Page<PostVM> getPosts(@PageableDefault(sort = "id", direction = Direction.DESC) Pageable page){
		return postService.getPosts(page).map(PostVM::new);
	}

}

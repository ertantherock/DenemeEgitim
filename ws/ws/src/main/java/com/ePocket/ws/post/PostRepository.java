package com.ePocket.ws.post;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ePocket.ws.user.User;

public interface PostRepository extends JpaRepository<Post, Long> {
	Page<Post> findByUser(User user, Pageable page);
}

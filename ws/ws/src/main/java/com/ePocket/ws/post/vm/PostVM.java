package com.ePocket.ws.post.vm;

import java.util.Date;

import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ePocket.ws.post.Post;
import com.ePocket.ws.user.User;
import com.ePocket.ws.user.vm.VMUser;

import lombok.Data;

@Data
public class PostVM {
	
	private Long id;
	private String contentTitle;
	private String contentLabel;
	private String contentPost;
	private String contentLink;
	private long timeStampDate;
	private VMUser user;
	
	public PostVM(Post post) {
		this.setId(post.getId());
		this.setContentLabel(post.getContentLabel());
		this.setContentLink(post.getContentLink());
		this.setContentPost(post.getContentPost());
		this.setContentTitle(post.getContentTitle());
		this.setTimeStampDate(post.getTimeStampDate().getTime());
		this.setUser(new VMUser(post.getUser()));
	}

}

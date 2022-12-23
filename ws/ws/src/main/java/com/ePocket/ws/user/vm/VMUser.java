package com.ePocket.ws.user.vm;

import com.ePocket.ws.user.User;

import lombok.Data;


@Data
public class VMUser {
	
	

	private String username;
	
	private String mail;
	
	private String image;
	
	public VMUser(User user) {
		this.setUsername(user.getUsername());
		this.setMail(user.getMail());
		this.setImage(user.getImage());
}

}

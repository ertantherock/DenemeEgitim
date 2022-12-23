package com.ePocket.ws.post;


import java.util.Date;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ePocket.ws.user.User;

import lombok.Data;

@Data
@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column
	private String contentTitle;
	
	@Column
	private String contentLabel;
	
	@Column
	private String contentPost;
	
	@Column
	private String contentLink;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;

	
	@Temporal(TemporalType.TIMESTAMP)
	private Date timeStampDate;
	


	
	
	


}

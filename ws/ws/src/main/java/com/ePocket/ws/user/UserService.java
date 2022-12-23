package com.ePocket.ws.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ePocket.ws.error.NotFound;
import com.ePocket.ws.user.vm.VMUpdateUser;

@Service // Service layer for users. Layered arc.
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepository) {
		this.userRepository=userRepository;
		this.passwordEncoder= new BCryptPasswordEncoder();
	}
	

	
	public void save(User user) {
		String encryptedPassword= this.passwordEncoder.encode(user.getPassword());
		user.setPassword(encryptedPassword);
		userRepository.save(user);
		
	}



	public User getByUsername(String username) {
		User inDB = userRepository.findByUsername(username);
		if(inDB == null ) {
			throw new NotFound();
		
		}
		return inDB;
		
	
		
	}
	
	public Page<User> getUsers(Pageable page, User user) {
		if(user != null) {
			return userRepository.findByUsernameNot(user.getUsername(), page);
		}
		return userRepository.findAll(page);
	}



	public User updateUser(String username, VMUpdateUser updatedUser) {
		User inDB = getByUsername(username);
		inDB.setUsername(updatedUser.getUsername());
		if (updatedUser.getImage() != null) {
			inDB.setImage(updatedUser.getImage());
		}
		return userRepository.save(inDB);
		 
	}
	
	
	





}

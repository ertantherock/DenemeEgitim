package com.ePocket.ws.user;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import org.springframework.data.domain.Pageable;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.spel.spi.Function;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ePocket.ws.error.ApiError;
import com.ePocket.ws.shared.ResponseGeneric;
import com.ePocket.ws.shared.UserCurrent;
import com.ePocket.ws.user.vm.VMUpdateUser;
import com.ePocket.ws.user.vm.VMUser;
import com.fasterxml.jackson.annotation.JsonView;



@RestController
public class UserController {
	
	
	
	@Autowired
	UserService userService;
	
	
	@PostMapping("/api/1.0/users")
	public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
		ApiError error = new ApiError(400, "Validation error", "/api/1.0/users");
		Map<String, String> validationErrors = new HashMap<>();

		
		String username = user.getUsername();
		String mail = user.getMail();
		String password = user.getPassword();
		
		if(username == null  || username.isEmpty()) {
			validationErrors.put("username", "username cannot be null");
			
			
			
		}
		
		if(mail == null  || mail.isEmpty()) {
			validationErrors.put("mail", "Mail Cannot Be Null");
			
			
			
		}
		if(password == null  || password.isEmpty()) {
			validationErrors.put("password", "Password Cannot Be Null");
			
			
			
		}
		if(validationErrors.size() > 0) {
			error.setValidationErrors(validationErrors);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
			
		}
		userService.save(user);
		return ResponseEntity.ok(new ResponseGeneric("User Created"));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiError solveValidatonProblem(MethodArgumentNotValidException exception) {
		ApiError error = new ApiError(400, "Validation error", "/api/1.0/users");
		Map<String, String> validationErrors = new HashMap<>();
		for(FieldError fieldError:exception.getBindingResult().getFieldErrors()) {
			
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		
		}
		error.setValidationErrors(validationErrors);
		return error;    
	}    
	
	@GetMapping("/api/1.0/users")	
	Page<VMUser> getUsers(Pageable page, @UserCurrent User user) {		
		return userService.getUsers(page, user).map(VMUser::new);
		
	}
	
	
	  @GetMapping("/api/1.0/users/{username}") VMUser getUser(@PathVariable String
	  username) { 
		  User user = userService.getByUsername(username); 
	  return new VMUser(user); 
	  }
	  
	 

	  @PutMapping("/api/1.0/users/{username}")
	  VMUser updateUser(@RequestBody VMUpdateUser updatedUser, @PathVariable String
	username) {
		 User user = userService.updateUser(username,updatedUser);
		 return new VMUser(user);
	  }
	 
	
	
}

package com.ePocket.ws.auth;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.validator.internal.IgnoreForbiddenApisErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.ePocket.ws.error.ApiError;
import com.ePocket.ws.user.User;
import com.ePocket.ws.user.UserRepository;

@RestController
public class AuthController {
	
	@Autowired
	UserRepository userRepository;
	
	PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
	
	
	@PostMapping("/api/1.0/auth")
	ResponseEntity<?> handleAuthentication(@RequestHeader (name="Authorization", required = false) String authorization) {
		if(authorization == null) {
			ApiError error = new ApiError(401, "Unauthorized Request", "/api/1.0/auth");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
		}
		String base64encoded = authorization.split("Basic ")[1]; //Encoded part
		String decoded = new String(Base64.getDecoder().decode(base64encoded)); //Decoded part of password.
		String[] parts = decoded.split(":"); //["kullanıcı1", "Sifre"]
		String username = parts[0];
		String password = parts[1];
		User inDB = userRepository.findByUsername(username);
		if (inDB== null) {
			ApiError error = new ApiError(401, "Unauthorized Request", "/api/1.0/auth");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
			
		}
		
		String hashedPassword = inDB.getPassword();
		if (!passwordEncoder.matches(password, hashedPassword)) {
			ApiError error = new ApiError(401, "Unauthorized Request", "/api/1.0/auth");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);

		}
//		
//		//username, displayName, image
//		Map<String, String> responseBody = new HashMap<>();
//		responseBody.put("username", inDB.getUserName());
//		responseBody.put("username", inDB.getDisplayName());
//		responseBody.put("username", inDB.getImage());
//		
 		return ResponseEntity.ok().build();
		
	}
	

}
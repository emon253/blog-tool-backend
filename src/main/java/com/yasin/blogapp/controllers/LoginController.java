package com.yasin.blogapp.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yasin.blogapp.consts.UserRole;
import com.yasin.blogapp.entity.User;
import com.yasin.blogapp.payloads.AuthRequest;
import com.yasin.blogapp.payloads.AuthResponse;
import com.yasin.blogapp.security_config.JwtTokenUtil;
import com.yasin.blogapp.services.IUserService;
import com.yasin.blogapp.services.UserServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserServiceImpl userService;

	@Autowired
	JwtTokenUtil jwtTokenUtil;

	static final Logger logger = Logger.getLogger(LoginController.class.getName());

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
		AuthResponse response = new AuthResponse();

		try {

			logger.log(Level.INFO, "authenticating user using authetication provider...");
			request.setRole(UserRole.ADMIN.name());
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
					request.getUsername(), request.getPassword());
			System.out.println(authToken);

			Authentication authentication = this.authenticationManager.authenticate(authToken);
			System.out.println(request);

			String token = this.jwtTokenUtil.generateJwtToken(authentication);

			response.setAccessToken(token);
			response.setUser(userService.getUserByUsername(request.getUsername()));
			response.setMessage("Login Success");
			logger.log(Level.INFO, "sending response to the user...");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (AuthenticationException e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, "user authentication fail due to incorrect user or password...");
			response.setAccessToken("");
			response.setMessage("Incorrect username or password...");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}

	}
}

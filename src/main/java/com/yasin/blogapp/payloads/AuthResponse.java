package com.yasin.blogapp.payloads;

import com.yasin.blogapp.entity.User;

import lombok.Data;

@Data
public class AuthResponse {
	
	private User user;
	
	private String accessToken;
	
	private String message;
}

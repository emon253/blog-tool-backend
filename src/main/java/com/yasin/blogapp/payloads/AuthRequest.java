package com.yasin.blogapp.payloads;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequest {

	@NotBlank(message = "Username is required")
	private String username;

	private String email;

	@NotBlank(message = "Password is required")
	private String password;

	private String role;
}

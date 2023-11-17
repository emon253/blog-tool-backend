package com.yasin.blogapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yasin.blogapp.entity.User;
import com.yasin.blogapp.exceptions.ResourceNotFoundException;
import com.yasin.blogapp.exceptions.UserNotFoundWithEmailException;
import com.yasin.blogapp.payloads.UserDetailsImpl;
import com.yasin.blogapp.repositories.UserRepository;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}

	@Override
	public User getUserById(Long id) {
		return this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "userid"));
	}

	@Override
	public User createUser(User user) {

		String encodedPassword = this.passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		User createdUser = this.userRepository.save(user);
		createdUser.setPassword("");
		return createdUser;
	}

	@Override
	public User updateUser(User user) {
		User updatedUser = this.userRepository.save(user);
		return updatedUser;

	}

	@Override
	public void deleteUserById(Long id) {
		this.userRepository.delete(this.getUserById(id));
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundWithEmailException("User not found with username " + username));
		return new UserDetailsImpl(user);
	}

	@Override
	public User getUserByUsername(String username) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundWithEmailException("User not found with username " + username));
		return user;
	}

}

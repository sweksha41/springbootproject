package com.module.usermgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import io.swagger.annotations.Api;
//import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.module.usermgmt.model.User;
import com.module.usermgmt.repository.UserRepository;

import lombok.RequiredArgsConstructor;

//@Api(tags = "Users API", value = "All the User related API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
//	public UserController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder)
//	{
//		
//	}

	@GetMapping("/")
	public String greeting(/* @RequestParam(value = "name", defaultValue = "World") String name */) {
		return "Vishal";
	 }
 
	@PostMapping("/register")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		try { 
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			User _user = userRepository.save(user);
			return new ResponseEntity<>(_user, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

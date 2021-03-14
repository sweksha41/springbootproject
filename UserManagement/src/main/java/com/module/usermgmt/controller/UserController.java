package com.module.usermgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import io.swagger.annotations.Api;
//import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.module.usermgmt.model.User;
import com.module.usermgmt.repository.UserDetailsServiceImpl;
import com.module.usermgmt.repository.UserRepository;
import com.module.usermgmt.util.JwtTokenUtil;

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
	
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	
//	public UserController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder)
//	{
//		
//	}
	
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody User authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsServiceImpl
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(token);
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}


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

package com.module.usermgmt.repository;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.module.usermgmt.model.User;
import com.module.usermgmt.security.GrantedPermission;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUsername(username);
		if(user == null)
			 throw new UsernameNotFoundException(username);
		
		
		int isAdmin = user.getIsAdmin();
		GrantedPermission grantedPermission = new GrantedPermission(String.valueOf(isAdmin));
		return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), Arrays.asList(grantedPermission));
	}

}

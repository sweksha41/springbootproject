package com.module.usermgmt.util;
import java.io.Serializable;
import java.util.Set;

import com.module.usermgmt.security.GrantedPermission;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private final String userName;
	private final Set<GrantedPermission> authorities;
	

	public JwtResponse(String jwttoken, String userName,  Set<GrantedPermission> authorities) {
		this.jwttoken = jwttoken;
		this.userName = userName;
		this.authorities = authorities;
	}

	public String getToken() {
		return this.jwttoken;
	}
	
	public String getUsername() {
		return this.userName;
	}
	
	public Set<GrantedPermission> getAuthorities() {
		return this.authorities;
	}
}
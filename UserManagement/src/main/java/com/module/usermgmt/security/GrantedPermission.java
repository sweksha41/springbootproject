package com.module.usermgmt.security;

import org.springframework.security.core.GrantedAuthority;

public class GrantedPermission implements GrantedAuthority{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	String authority = "0";
	
	public GrantedPermission(String permission)
	{
		authority = permission;
	}
	
	@Override
	public String getAuthority() {
		return authority;
	}

}

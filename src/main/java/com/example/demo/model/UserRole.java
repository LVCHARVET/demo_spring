package com.example.demo.model;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
	ADMIN, USER, GUEST;

	public String getAuthority() {
		return name();
	}
}

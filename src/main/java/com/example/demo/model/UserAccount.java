package com.example.demo.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String password;

	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private UserRole role;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<GrantedAuthority> authorities = new ArrayList<>();

	public UserAccount() {
	}

	public UserAccount(String username, String password, UserRole role, String... authorities) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.authorities = Arrays.stream(authorities).map(SimpleGrantedAuthority::new).map(GrantedAuthority.class::cast)
				.toList();
	}

	public UserDetails asUser() {
		return User.withDefaultPasswordEncoder().username(getUsername()).password(getPassword())
				.authorities("ROLE_" + getRole().name()).build();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public List<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

}

package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserAccount;
import com.example.demo.repository.UserAccountRepository;

import jakarta.annotation.PostConstruct;

@Service
public class UserAccountService {

	@Autowired
	private UserAccountRepository userAccountRepository;

	@PostConstruct
	public void initUsers() {
		userAccountRepository.save(new UserAccount("user", "user", "USER"));
		userAccountRepository.save(new UserAccount("admin", "admin", "ADMIN"));
	}

}

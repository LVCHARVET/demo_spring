package com.example.demo.serveur;

import org.springframework.stereotype.Service;

import com.example.demo.configuration.Config;


 @Service
public class UserService {
	private Config config;

	public UserService(Config config) {
		this.config = config;
		System.out.println("UserService Constructor " + config.toString());
	}

	public Config getConfig() {
		return config;
	}
	
}

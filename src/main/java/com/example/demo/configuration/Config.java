package com.example.demo.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
	
	private static String name="Config Bean";

	public Config() {
		System.out.println("Configuration constructor");
	}

	public String getName() {
		return name;
	}

}

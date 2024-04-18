package com.example.demo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

	private static String name = "Config Bean";

	public Config() {
		System.out.println("Configuration constructor");
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return "Config [ name = " + getName() + " ]";
	}

}

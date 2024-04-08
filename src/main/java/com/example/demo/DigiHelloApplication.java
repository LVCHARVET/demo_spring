package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.configuration.Config;

@SpringBootApplication
public class DigiHelloApplication {

	@Autowired
	private Config config;
	
	public static void main(String[] args) {
		SpringApplication.run(DigiHelloApplication.class, args);		
	}
	
	public void run(String... args) throws Exception {
		System.out.println(config);
	}

}

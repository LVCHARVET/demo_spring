package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.config.Config;

@SpringBootApplication
public class DigiHelloApplication implements CommandLineRunner {

	@Autowired
	private Config config;

	public static void main(String[] args) {
		SpringApplication.run(DigiHelloApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(config);
	}

}

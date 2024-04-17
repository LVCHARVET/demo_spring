package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.model.UserAccount;
import com.example.demo.repository.UserAccountRepository;

@Configuration
public class SecurityConfig {

//	@Bean
//	UserDetailsService userDetailsService() {
//
//		UserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//		userDetailsManager
//				.createUser(User.withDefaultPasswordEncoder().username("user").password("user").roles("USER").build());
//		userDetailsManager.createUser(
//				User.withDefaultPasswordEncoder().username("admin").password("admin").roles("ADMIN").build());
//
//		return userDetailsManager;
//	}

	@Bean
	public UserDetailsService userDetailsService(UserAccountRepository userAccountRepository) {
		return username -> userAccountRepository.findByUsername(username).asUser();
	}

	@Bean
	public SecurityFilterChain configureSecurity(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests().requestMatchers("/login").permitAll()
									.requestMatchers("/", "/home").authenticated()
									.requestMatchers(HttpMethod.GET, "/villes/**").authenticated()
									.requestMatchers("/admin").hasRole("ADMIN")
									.requestMatchers(HttpMethod.POST, "/villes/**").hasRole("ADMIN").anyRequest().denyAll()
									.and().formLogin().and().httpBasic();
		return http.build();
	}
}

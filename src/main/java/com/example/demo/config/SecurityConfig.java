package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.repository.UserAccountRepository;

@Configuration
public class SecurityConfig {

	@Bean
	public UserDetailsService userDetailsService(UserAccountRepository userAccountRepository) {
		return username -> userAccountRepository.findByUsername(username).asUser();
	}

	@Bean
	public SecurityFilterChain configureSecurity(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(request -> request
						.requestMatchers("/login").permitAll()
						.requestMatchers("/", "/hello", "/profil").authenticated()
						.requestMatchers("/villes/delete/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.GET, "/villes/**").authenticated()
						.requestMatchers(HttpMethod.POST, "/villes/**").hasRole("ADMIN").anyRequest().denyAll())
				.formLogin(Customizer.withDefaults())
				.httpBasic(Customizer.withDefaults());
		return http.build();
	}
}

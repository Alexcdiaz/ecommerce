package com.ecommerce.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringBootSecurity {
	
	@Bean
	public UserDetailServiceImpl userDetailService() {
		return new UserDetailServiceImpl();
	}
	
	@Bean
	public BCryptPasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers("/administrador/**").hasRole("ADMIN")
		.antMatchers("/productos/**").hasRole("ADMIN")
		.and().formLogin().loginPage("/usuario/login")
		.permitAll().defaultSuccessUrl("/usuario/acceder");
		
		http.headers().frameOptions().sameOrigin();
		
		return http.build();
	}
	
	
}

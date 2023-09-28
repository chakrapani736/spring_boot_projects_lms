package com.te.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class EmployeeSecurityConfig {
	@Autowired
	private CustomAuthenticationSuccessHandler successHandler;

	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService gerDetailsService() {
		return new CustomUserDetailsService();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(gerDetailsService());
		authenticationProvider.setPasswordEncoder(getBCryptPasswordEncoder());
		return authenticationProvider;
	}

	@Bean
	public SecurityFilterChain getFilterChain(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeHttpRequests().requestMatchers("/**").permitAll().requestMatchers("/user/**")
				.hasRole("USER").requestMatchers("/admin/**").hasRole("ADMIN").and().formLogin().loginPage("/signin")
				.loginProcessingUrl("/login").successHandler(successHandler);
		return http.build();
	}
}

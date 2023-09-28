package com.te.spring.config;

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
public class UserSecurityConfig {

	@Bean
	public BCryptPasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public UserDetailsService getUserDetails() {
		return new CustomUserDetails();
	}
	@Bean
	public DaoAuthenticationProvider getAuthentication() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(getUserDetails());
		provider.setPasswordEncoder(getEncoder());
		return provider;

	}
	@Bean
	public SecurityFilterChain getSecurityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeHttpRequests()
		.requestMatchers("/**").permitAll()
		.requestMatchers("/user/**").hasRole("USER")
		.requestMatchers("/admin/**").hasRole("ADMIN")
		.and()
		.formLogin()
		//.loginPage("/signin").loginProcessingUrl("/userlogin")
		.defaultSuccessUrl("/userlogin").permitAll();
		return http.build();
	}
}

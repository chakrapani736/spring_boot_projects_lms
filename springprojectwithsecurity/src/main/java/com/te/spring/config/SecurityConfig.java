package com.te.spring.config;

import java.net.http.HttpRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class SecurityConfig{
	@Bean
	public UserDetailsService getService() {
		return new CustomUserDetailsService();
	}
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public DaoAuthenticationProvider getProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(getService());
		daoAuthenticationProvider.setPasswordEncoder(encoder());
		return daoAuthenticationProvider;
	}

	@Bean
	public SecurityFilterChain changingDefault(HttpSecurity http) throws Exception{
		http.csrf().disable()
		.authorizeHttpRequests().requestMatchers("/**").permitAll()
		.requestMatchers("/user/**").hasRole("User")
		.requestMatchers("/admin/**").hasRole("Admin")
		.and()
		.formLogin().loginPage("/signin").loginProcessingUrl("/login")
		.defaultSuccessUrl("/login").permitAll();
		
		return http.build();
	}
	
}

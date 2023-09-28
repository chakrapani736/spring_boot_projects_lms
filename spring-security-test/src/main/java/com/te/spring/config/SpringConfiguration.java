package com.te.spring.config;

import java.util.ArrayList;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SpringConfiguration extends WebSecurityConfigurerAdapter{

	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		List<UserDetails>list = new ArrayList<>();
		list.add(User.withDefaultPasswordEncoder().username("klpani").password("12345").roles("admin").build());
		return new InMemoryUserDetailsManager(list);
	}
  
	
}

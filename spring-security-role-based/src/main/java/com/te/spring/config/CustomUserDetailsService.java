package com.te.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.te.spring.entity.Employee;
import com.te.spring.repository.EmployeeRepository;

public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private EmployeeRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee employee = repository.findByuserName(username);
		if (employee != null) {
			return new CustomUserDetails(employee);
		} else {
			throw new UsernameNotFoundException("user name not found in data base");
		}
	}

}

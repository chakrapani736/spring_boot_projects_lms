package com.te.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.te.spring.entity.User;
import com.te.spring.repository.AppRepository;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private AppRepository repo;
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		User findUserByName = repo.findByuserName(userName);
		if(findUserByName!=null) {
			return new CustomUser(findUserByName);
		}else {
			throw new UsernameNotFoundException("not found");
		}
	}

}

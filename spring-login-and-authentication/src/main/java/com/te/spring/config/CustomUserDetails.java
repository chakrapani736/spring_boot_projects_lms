package com.te.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.te.spring.model.UserInfo;
import com.te.spring.repository.UserInfoRepository;

public class CustomUserDetails implements UserDetailsService {
   @Autowired
	private UserInfoRepository repository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo user = repository.findByuserName(username);
		if(user!=null) {
			return new CustomUserInfoDetails(user);
		}
		return null;
		
	}

}

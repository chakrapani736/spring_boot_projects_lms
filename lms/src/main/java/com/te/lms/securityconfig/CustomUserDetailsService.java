package com.te.lms.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.te.lms.entity.EmployeePrimaryInfo;
import com.te.lms.repository.LmsEmployeeRepository;

public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private LmsEmployeeRepository lmsEmployeeRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		EmployeePrimaryInfo primaryInfo = lmsEmployeeRepository.findByemployeeEmail(username);
		if(primaryInfo!=null) return new CustomUserDetails(primaryInfo);
		else throw new UsernameNotFoundException("Invalid User Credential");
	
	}

}

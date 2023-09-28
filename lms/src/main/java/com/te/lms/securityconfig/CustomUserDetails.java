package com.te.lms.securityconfig;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.te.lms.entity.EmployeePrimaryInfo;

public class CustomUserDetails implements UserDetails {

	private EmployeePrimaryInfo employeePrimaryInfo;
	
	public CustomUserDetails(EmployeePrimaryInfo employeePrimaryInfo) {
		this.employeePrimaryInfo = employeePrimaryInfo;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(employeePrimaryInfo.getEmployeeEmail());
		
		return Arrays.asList(authority);
	}

	@Override
	public String getPassword() {
		return employeePrimaryInfo.getPassword();
	}

	@Override
	public String getUsername() {
		return employeePrimaryInfo.getEmployeeEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}

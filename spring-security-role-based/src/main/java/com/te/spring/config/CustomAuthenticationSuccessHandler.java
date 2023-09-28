package com.te.spring.config;

import java.io.IOException;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private String role;
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		Set<String> authorityListToSet = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		try {
			role="ROLE_"+request.getParameter("userRole").toUpperCase();
			if(authorityListToSet.contains("ROLE_USER")&&role.equalsIgnoreCase("ROLE_USER")) {
				response.sendRedirect("/user/profile");
			}else {
				response.sendRedirect("/admin/profile");
			}
		}catch(IOException e){
			throw new IOException("unknown details");
		}finally {
			role=null;
		}
	
	}

}

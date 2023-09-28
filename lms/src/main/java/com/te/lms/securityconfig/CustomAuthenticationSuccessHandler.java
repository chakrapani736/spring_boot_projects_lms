package com.te.lms.securityconfig;

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
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
	private String role;
		@Override
		public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,Authentication authentication) throws IOException, ServletException {
			Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
			try {
				if (roles.contains("ROLE_ADMIN")) {
					response.sendRedirect("/admin/getRequestedEmployee");
				} else if (roles.contains("ROLE_MENTOR")) {
					response.sendRedirect("/mentor");
				} else
					response.sendRedirect("/employee");
			} finally {
				role = null;
			}

			
		}

}

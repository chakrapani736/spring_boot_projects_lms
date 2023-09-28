package com.te.spring.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	private String secretkey = "${app.secretkey}";

	public String generateToken(String subject) {
		return Jwts.builder().setSubject(subject).setIssuer("KL PANI").setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(10)))
				.signWith(SignatureAlgorithm.HS256, secretkey.getBytes()).compact();
	}

	public Claims getClaims(String token) {
		return Jwts.parser().setSigningKey(secretkey.getBytes()).parseClaimsJws(token).getBody();
	}

	public boolean isTokenExpired(String token) {
		Date expiration = getClaims(token).getExpiration();
		return expiration.after(new Date(System.currentTimeMillis()));
	}

}

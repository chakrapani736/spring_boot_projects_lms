package com.te.spring.jsonwebtoken;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
	private String secret = "${app.secret}";
	
	//generating token
	public String generateToken(String subject) {
		return Jwts.builder()
		.setSubject(subject)
		.setIssuer("KL Pani")
		.setIssuedAt(new Date(System.currentTimeMillis()))
		.setExpiration(new Date(System.currentTimeMillis()+ TimeUnit.MINUTES.toMillis(4)))
		.signWith(SignatureAlgorithm.HS256,secret.getBytes())
		.compact();
	}
	//claiming token
	public Claims getClaims(String token) {
		return Jwts.parser()
				.setSigningKey(secret.getBytes())
				.parseClaimsJws(token)
				.getBody();
	}
	//get Expiation
	
	

}

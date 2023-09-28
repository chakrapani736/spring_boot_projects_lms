package com.te.lms.securityconfig;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class AutoGeneratePassword {
	public String generatePassword(int length) {
		final String characters = "ASDFGHJKLZXCVBNMPOIUYTREWQzxcvbnmlkjhgfdsaqwertyuiop1234567890@#$%^&*()_+-=<>!";
			String generatedPsw="";
			Random random  = new Random();
			for (int i = 0; i < length; i++) {
				int index = random.nextInt(characters.length());
				generatedPsw+=characters.charAt(index);
			}
			return generatedPsw;
		}
	public String generateId() {
		final String characters = "1234567890";
		String generatedId="";
		Random random  = new Random();
		for (int i = 0; i < 4; i++) {
			int index = random.nextInt(characters.length());
			generatedId+=characters.charAt(index);
		}
		return generatedId;
	}

}

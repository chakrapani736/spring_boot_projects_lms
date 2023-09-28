package com.te.spring.response;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class BookAuthorResponse {
	private boolean error;
	private int status;
	private String message;
	private Object liBooks;
}

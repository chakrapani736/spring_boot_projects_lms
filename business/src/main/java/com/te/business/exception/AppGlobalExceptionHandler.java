package com.te.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppGlobalExceptionHandler {
	// handling exception for invalid output
	@ExceptionHandler(value = InvalidInputException.class)
	public ResponseEntity<String> getInvalidOutputException(InvalidInputException e) {
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.getMessage());
	}

	// handling exception for entity not found
	@ExceptionHandler(value = EntityNotFoundException1.class)
	public ResponseEntity<String> getEntityNotFoundException(EntityNotFoundException1 e) {
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.getMessage());
	}
}

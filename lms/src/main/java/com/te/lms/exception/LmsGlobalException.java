package com.te.lms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
//global exception class
@ControllerAdvice
public class LmsGlobalException {

	//handling Approval Exception and sending response
	
	@ExceptionHandler(ApprovalException.class)
	public ResponseEntity<String> getApprovalException(ApprovalException e) {
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.getMessage());
	}
		//handling EmployeeNotFound Exception and sending response

	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<String> getEmployeeNotFoundException(EmployeeNotFoundException e) {
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.getMessage());
	}
	
	//handling MentorNotFound Exception and sending response

	@ExceptionHandler(MentorNotFoundException.class)
	public ResponseEntity<String> getMentorNotFoundException(MentorNotFoundException e) {
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.getMessage());
	}
	//handling BatchNotFound Exception and sending response

	@ExceptionHandler(BatchNotFoundException.class)
	public ResponseEntity<String> getBatchNotFoundException(BatchNotFoundException e) {
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.getMessage());
	}
	//handling MockNotFound Exception and sending response

	@ExceptionHandler(MockNotFoundException.class)
	public ResponseEntity<String> getMockNotFoundException(MockNotFoundException e) {
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.getMessage());
	}
	
	//handling EmailNotFound Exception and sending response

	@ExceptionHandler(EmailNotFoundException.class)
	public ResponseEntity<String> getEmailNotFoundException(EmailNotFoundException e) {
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.getMessage());
	}
}

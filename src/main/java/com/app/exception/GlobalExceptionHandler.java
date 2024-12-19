package com.app.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(EnquiryNotFoundException.class)
	public ResponseEntity<String>EnquiryNotFoundException(EnquiryNotFoundException ae)
	{
		String msg ="Enquiry Not Found for Customer ID";
		return new ResponseEntity<String>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}


}

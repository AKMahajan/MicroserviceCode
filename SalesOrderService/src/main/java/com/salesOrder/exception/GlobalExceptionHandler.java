package com.salesOrder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public String handleMethod(CustomerNotFoundException e) {
		return e.getMessage();
	}
	
	@ExceptionHandler(ItemNotFoundException.class)
	public String handleMethod1(ItemNotFoundException e) {
		return e.getMessage();
		
	}

}

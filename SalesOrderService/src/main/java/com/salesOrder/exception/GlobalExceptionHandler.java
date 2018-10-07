package com.salesOrder.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

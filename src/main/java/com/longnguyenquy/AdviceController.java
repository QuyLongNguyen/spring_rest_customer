package com.longnguyenquy;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.longnguyenquy.entity.ErrorResponser;
import com.longnguyenquy.exception.CustomerNotFoundException;

@ControllerAdvice
public class AdviceController {

	@ExceptionHandler
	public ResponseEntity<ErrorResponser> handleException(CustomerNotFoundException exc){
		
		ErrorResponser errorResponser = new ErrorResponser();
		errorResponser.setStatus(HttpStatus.NOT_FOUND.value());
		errorResponser.setMessage(exc.getMessage());
		errorResponser.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(errorResponser,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponser> handleException(NumberFormatException exc){
		
		ErrorResponser errorResponser = new ErrorResponser(HttpStatus.BAD_REQUEST.value(),
				exc.getMessage(),System.currentTimeMillis());
		
		return new ResponseEntity<>(errorResponser,HttpStatus.BAD_REQUEST);
		
	}
}

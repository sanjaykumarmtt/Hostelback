package com.Hostel_management.Hostel.ExceptionHan;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


//@ControllerAdvice
public class GolableException {
	
	@ExceptionHandler(HostelException.class)
	public ResponseEntity<String> banknotfound(HostelException bankexc){
		return new ResponseEntity<>(bankexc.getMessage(),HttpStatus.NOT_FOUND);	
		 
	}

}

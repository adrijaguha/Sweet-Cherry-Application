package com.capgemini.sweetcherry.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandling {

	@ExceptionHandler({ NoSuchCupcakeExistsException.class, NoSuchOrderExistsException.class, NoSuchUserExistsException.class,
			NoSuchAddressExistsException.class, PaymentFailedException.class, InvalidIdException.class,
			UserNameAndPasswordDoNotMatchRegularExpressionException.class, CupcakeNotAvailableException.class })
	public ResponseEntity<Object> exceptionHandler(Exception e) {
		System.out.println(e.getMessage());
		return new ResponseEntity<Object>(e.getMessage(),HttpStatus.NOT_FOUND);
	}

}

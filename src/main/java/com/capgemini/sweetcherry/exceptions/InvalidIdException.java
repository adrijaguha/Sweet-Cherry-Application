package com.capgemini.sweetcherry.exceptions;

public class InvalidIdException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidIdException() {
		super("Invalid Id..");
	}
	
	public InvalidIdException(String message) {
		super(message) ;
	}
}

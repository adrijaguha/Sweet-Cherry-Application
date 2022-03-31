package com.capgemini.sweetcherry.exceptions;

public class NoSuchCupcakeExistsException extends RuntimeException {
	
	
	private static final long serialVersionUID = 1L;

	public NoSuchCupcakeExistsException() {
      
		super("No such cupcake exists...");
	}

	public NoSuchCupcakeExistsException(String message) {
		super(message);
	}

}
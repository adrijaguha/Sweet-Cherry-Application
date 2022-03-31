package com.capgemini.sweetcherry.exceptions;

public class NoSuchCupcakeCategoryExistsException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public NoSuchCupcakeCategoryExistsException() {
      
		super("No such cupcake exists...");
	}

	public NoSuchCupcakeCategoryExistsException(String message) {
		super(message);
	}


}

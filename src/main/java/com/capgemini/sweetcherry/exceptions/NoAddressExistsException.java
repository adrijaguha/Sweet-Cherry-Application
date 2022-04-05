package com.capgemini.sweetcherry.exceptions;

public class NoAddressExistsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public NoAddressExistsException() {
		super("No adress exists...");
	}

	public NoAddressExistsException(String message) {

		super(message);
	}
}
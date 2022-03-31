package com.capgemini.sweetcherry.exceptions;

//NoSuchAddressExists exception class extends the Exception class
public class NoSuchAddressExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	// Defining default constructor
	public NoSuchAddressExistsException() {
		super("No such address exists...");
	}

	// Defining parameterized constructor with String parameter
	public NoSuchAddressExistsException(String message) {

		// Calling super constructor by passing the error message
		super(message);
	}
}

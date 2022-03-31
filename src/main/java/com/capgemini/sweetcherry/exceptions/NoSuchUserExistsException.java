package com.capgemini.sweetcherry.exceptions;

public class NoSuchUserExistsException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public NoSuchUserExistsException(String message) {
		super(message);
	}
	public NoSuchUserExistsException() {
		super("No such user exists");
	}
}
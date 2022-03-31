package com.capgemini.sweetcherry.exceptions;

public class NoSuchOrderExistsException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public NoSuchOrderExistsException(String message) {
		super(message);
	}

	public NoSuchOrderExistsException() {
		super("No such order exists");
	}
}

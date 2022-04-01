package com.capgemini.sweetcherry.exceptions;

public class CupcakeNotAvailableException extends RuntimeException {

	public CupcakeNotAvailableException() {
		super("Cupcakes not available");
	}
	public CupcakeNotAvailableException(String message) {
		super(message);
	}
}

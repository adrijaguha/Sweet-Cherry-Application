package com.capgemini.sweetcherry.exceptions;

public class PaymentFailedException extends RuntimeException {
	
	static final long serialVersionUID = 1L;
	public PaymentFailedException() {
		super("payment failed");
	}
	public PaymentFailedException(String message) {
		super(message);
	}
}
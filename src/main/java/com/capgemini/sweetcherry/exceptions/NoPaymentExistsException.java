package com.capgemini.sweetcherry.exceptions;

public class NoPaymentExistsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
		public NoPaymentExistsException() {
			super("No payment details exists...");
		}

		public NoPaymentExistsException(String message) {

			super(message);
		}
}

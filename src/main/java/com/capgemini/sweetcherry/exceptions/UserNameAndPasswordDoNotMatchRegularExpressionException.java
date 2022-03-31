package com.capgemini.sweetcherry.exceptions;

public class UserNameAndPasswordDoNotMatchRegularExpressionException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public UserNameAndPasswordDoNotMatchRegularExpressionException() {
		super("username and password does not match...");
	}

	public UserNameAndPasswordDoNotMatchRegularExpressionException(String msg) {
		super(msg);
	}
}
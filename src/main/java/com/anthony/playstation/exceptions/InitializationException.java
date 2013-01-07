package com.anthony.playstation.exceptions;

public class InitializationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7314474948627670820L;

	/**
	 * @param message
	 */
	public InitializationException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public InitializationException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public InitializationException(String message, Throwable cause) {
		super(message, cause);
	}

}
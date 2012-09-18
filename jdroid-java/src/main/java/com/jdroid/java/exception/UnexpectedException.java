package com.jdroid.java.exception;

public class UnexpectedException extends AbstractException {
	
	/**
	 * @param message
	 * @param cause
	 */
	public UnexpectedException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * @param message
	 */
	public UnexpectedException(String message) {
		super(message);
	}
	
	/**
	 * @param cause
	 */
	public UnexpectedException(Throwable cause) {
		super(cause);
	}
	
}

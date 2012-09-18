package com.jdroid.java.exception;

public abstract class AbstractException extends RuntimeException {
	
	/**
	 * Constructor
	 */
	public AbstractException() {
		super();
	}
	
	/**
	 * @param message
	 * @param cause
	 */
	public AbstractException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * @param message
	 */
	public AbstractException(String message) {
		super(message);
	}
	
	/**
	 * @param cause
	 */
	public AbstractException(Throwable cause) {
		super(cause);
	}
	
}

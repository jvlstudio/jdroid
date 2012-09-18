package com.jdroid.java.exception;

/**
 * Exception related with connectivity errors.
 * 
 * @author Estefania Caravatti
 */
public class ConnectionException extends ApplicationException {
	
	public ConnectionException(Throwable throwable) {
		super(throwable);
	}
	
	public ConnectionException(ErrorCode errorCode, Throwable throwable) {
		super(errorCode, throwable);
	}
	
	public ConnectionException(ErrorCode errorCode, String message) {
		super(errorCode, message);
	}
	
}

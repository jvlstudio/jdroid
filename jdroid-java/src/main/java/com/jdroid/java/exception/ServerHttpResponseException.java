package com.jdroid.java.exception;

import com.jdroid.java.exception.ErrorCode;
import com.jdroid.java.exception.HttpResponseException;

/**
 * Exception thrown when there are http errors communicating with the server.
 * 
 * @author Estefania Caravatti
 */
public class ServerHttpResponseException extends HttpResponseException {
	
	public ServerHttpResponseException(ErrorCode errorCode, Throwable throwable) {
		super(errorCode, throwable);
	}
	
	public ServerHttpResponseException(ErrorCode errorCode, String message) {
		super(errorCode, message);
	}
}

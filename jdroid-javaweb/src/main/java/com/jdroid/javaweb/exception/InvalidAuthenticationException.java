package com.jdroid.javaweb.exception;

import com.jdroid.java.exception.AbstractException;
import com.jdroid.java.exception.ErrorCode;

public class InvalidAuthenticationException extends AbstractException {
	
	private ErrorCode errorCode;
	
	public InvalidAuthenticationException(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
	
	/**
	 * @return the errorCode
	 */
	public ErrorCode getErrorCode() {
		return errorCode;
	}
	
}

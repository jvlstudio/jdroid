package com.jdroid.java.exception;

/**
 * Expected Application exception not related with business logic. <br>
 * For example a time out or I/O error.
 * 
 */
public class ApplicationException extends AbstractException {
	
	private ErrorCode errorCode;
	
	protected ApplicationException() {
		// Nothing by default.
	}
	
	protected ApplicationException(Throwable throwable) {
		super(throwable);
	}
	
	public ApplicationException(String errorMessage) {
		super(errorMessage);
	}
	
	public ApplicationException(ErrorCode errorCode, Throwable throwable) {
		super(throwable);
		this.errorCode = errorCode;
	}
	
	public ApplicationException(ErrorCode errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}
	
	/**
	 * @return the errorCode
	 */
	public ErrorCode getErrorCode() {
		return errorCode;
	}
}

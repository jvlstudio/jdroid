package com.jdroid.java.exception;

/**
 * Expected Business exceptions that are related to the app's business logic.
 */
public class BusinessException extends AbstractException {
	
	private ErrorCode errorCode;
	private Object[] errorCodeParameters;
	
	/**
	 * @param errorMessage The error message related to this exception.
	 */
	public BusinessException(String errorMessage) {
		super(errorMessage);
	}
	
	public BusinessException(ErrorCode errorCode, Object... errorCodeParameters) {
		this.errorCode = errorCode;
		this.errorCodeParameters = errorCodeParameters;
	}
	
	public BusinessException(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
	
	/**
	 * @return the errorCode
	 */
	public ErrorCode getErrorCode() {
		return errorCode;
	}
	
	/**
	 * @return the errorCodeParameters
	 */
	public Object[] getErrorCodeParameters() {
		return errorCodeParameters;
	}
}

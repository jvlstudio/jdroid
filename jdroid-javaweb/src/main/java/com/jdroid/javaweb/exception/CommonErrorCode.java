package com.jdroid.javaweb.exception;

import java.util.List;
import com.jdroid.java.exception.ApplicationException;
import com.jdroid.java.exception.BusinessException;
import com.jdroid.java.exception.ErrorCode;
import com.jdroid.java.utils.StringUtils;

/**
 * 
 * @author Maxi Rosson
 */
public enum CommonErrorCode implements ErrorCode {
	
	BAD_REQUEST(400),
	INVALID_CREDENTIALS(401),
	INVALID_USER_TOKEN(402),
	INVALID_API_VERSION(403),
	INVALID_SECURITY_TOKEN(404);
	
	private Integer statusCode;
	
	private CommonErrorCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	
	/**
	 * @see com.jdroid.java.exception.ErrorCode#newBusinessException(java.lang.Object[])
	 */
	@Override
	public BusinessException newBusinessException(Object... errorCodeParameters) {
		return new BusinessException(this, errorCodeParameters);
	}
	
	/**
	 * @see com.jdroid.java.exception.ErrorCode#newApplicationException(java.lang.Throwable)
	 */
	@Override
	public ApplicationException newApplicationException(Throwable throwable) {
		return new ApplicationException(this, throwable);
	}
	
	/**
	 * @see com.jdroid.java.exception.ErrorCode#newApplicationException(java.lang.String)
	 */
	@Override
	public ApplicationException newApplicationException(String message) {
		return new ApplicationException(this, message);
		
	}
	
	/**
	 * @see com.jdroid.java.exception.ErrorCode#getStatusCode()
	 */
	@Override
	public String getStatusCode() {
		return statusCode.toString();
	}
	
	/**
	 * @see com.jdroid.java.exception.ErrorCode#getResourceId()
	 */
	@Override
	public Integer getResourceId() {
		return null;
	}
	
	public void validateRequired(String value) {
		if (StringUtils.isEmpty(value)) {
			throw newBusinessException();
		}
	}
	
	public void validateRequired(Object value) {
		if (value == null) {
			throw newBusinessException();
		}
	}
	
	public void validateRequired(List<?> value) {
		if ((value == null) || value.isEmpty()) {
			throw newBusinessException();
		}
	}
	
	public void validatePositive(Integer value) {
		if (value <= 0) {
			throw newBusinessException();
		}
	}
	
	public void validatePositive(Float value) {
		if (value <= 0) {
			throw newBusinessException();
		}
	}
	
	public void validateMaximum(Integer value, Integer maximum) {
		if (value > maximum) {
			throw newBusinessException(maximum);
		}
	}
	
	public void validateMinimum(int value, int minimum) {
		if (value < minimum) {
			throw newBusinessException(minimum);
		}
	}
}

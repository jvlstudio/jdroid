package com.jdroid.java.exception;

/**
 * Common interface for all the possible errors of the application
 * 
 * @author Maxi Rosson
 */
public interface ErrorCode {
	
	/**
	 * @param errorCodeParameters The parameters for this {@link ErrorCode}'s message.
	 * @return A new {@link BusinessException} with this {@link ErrorCode}
	 */
	public BusinessException newBusinessException(Object... errorCodeParameters);
	
	/**
	 * @param throwable
	 * @return A new {@link ApplicationException} with this {@link ErrorCode}
	 */
	public ApplicationException newApplicationException(Throwable throwable);
	
	/**
	 * @param message
	 * @return A new {@link ApplicationException} with this message
	 */
	public ApplicationException newApplicationException(String message);
	
	/**
	 * @return The resource id
	 */
	public Integer getResourceId();
	
	/**
	 * @return The status code
	 */
	public String getStatusCode();
	
}

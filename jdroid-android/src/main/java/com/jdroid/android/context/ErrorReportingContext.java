package com.jdroid.android.context;

import com.jdroid.java.utils.PropertiesUtils;

/**
 * 
 * @author Maxi Rosson
 */
public class ErrorReportingContext {
	
	private static final ErrorReportingContext INSTANCE = new ErrorReportingContext();
	
	// Whether the error mail reporting service is enabled or not
	private static final Boolean MAIL_REPORTING = PropertiesUtils.getBooleanProperty("mail.reporting");
	
	private static final String MAIL_FROM = PropertiesUtils.getStringProperty("mail.from");
	
	private static final String MAIL_TO = PropertiesUtils.getStringProperty("mail.to");
	
	/**
	 * @return The {@link ErrorReportingContext} instance
	 */
	public static ErrorReportingContext get() {
		return INSTANCE;
	}
	
	public Boolean isMailReportingEnabled() {
		return MAIL_REPORTING;
	}
	
	public String getMailFrom() {
		return MAIL_FROM;
	}
	
	public String getMailTo() {
		return MAIL_TO;
	}
	
}

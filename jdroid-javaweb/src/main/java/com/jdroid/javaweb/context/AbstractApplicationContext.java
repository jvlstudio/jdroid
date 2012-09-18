package com.jdroid.javaweb.context;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContextAware;
import com.jdroid.javaweb.domain.Entity;

/**
 * The {@link AbstractApplicationContext}
 * 
 * @param <T>
 */
public abstract class AbstractApplicationContext<T extends Entity> implements ApplicationContextAware {
	
	private static AbstractApplicationContext<?> INSTANCE;
	
	private SecurityContextHolder<T> securityContextHolder;
	private org.springframework.context.ApplicationContext applicationContext;
	
	private String appName;
	private String appURL;
	private String appVersion;
	
	private String googleServerApiKey;
	private Boolean httpMockEnabled;
	private Integer httpMockSleepDuration;
	
	public AbstractApplicationContext() {
		INSTANCE = this;
	}
	
	public static AbstractApplicationContext<?> get() {
		return INSTANCE;
	}
	
	/**
	 * @return The {@link AbstractSecurityContext} instance
	 */
	public AbstractSecurityContext<T> getSecurityContext() {
		return securityContextHolder.getContext();
	}
	
	/**
	 * @param applicationUrl the application URL
	 */
	public void setApplicationURL(String applicationUrl) {
		if (StringUtils.isNotEmpty(applicationUrl)) {
			int pos = applicationUrl.indexOf(getAppName());
			if (pos != -1) {
				applicationUrl = applicationUrl.substring(0, pos + getAppName().length());
				appURL = applicationUrl;
			}
		}
	}
	
	public String getAppName() {
		return appName;
	}
	
	/**
	 * @param beanName The bean name
	 * @return The spring bean with the bean name
	 */
	public Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}
	
	/**
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(org.springframework.context.ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	public void setAppName(String appName) {
		this.appName = appName;
	}
	
	/**
	 * @return The application URL
	 */
	public String getAppURL() {
		return appURL;
	}
	
	/**
	 * @param appURL the appURL to set
	 */
	public void setAppURL(String appURL) {
		this.appURL = appURL;
	}
	
	/**
	 * @return the appVersion
	 */
	public String getAppVersion() {
		return appVersion;
	}
	
	/**
	 * @param appVersion the appVersion to set
	 */
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	
	public void setHttpMockSleepDuration(Integer httpMockSleepDuration) {
		this.httpMockSleepDuration = httpMockSleepDuration;
	}
	
	/**
	 * @param httpMockEnabled the httpMockEnabled to set
	 */
	public void setHttpMockEnabled(Boolean httpMockEnabled) {
		this.httpMockEnabled = httpMockEnabled;
	}
	
	public Boolean isHttpMockEnabled() {
		return httpMockEnabled;
	}
	
	public Integer getHttpMockSleepDuration() {
		return httpMockSleepDuration;
	}
	
	public String getGoogleServerApiKey() {
		return googleServerApiKey;
	}
	
	public void setGoogleServerApiKey(String googleServerApiKey) {
		this.googleServerApiKey = googleServerApiKey;
	}
	
	/**
	 * @param securityContextHolder The {@link SecurityContextHolder} to set
	 */
	public void setSecurityContextHolder(SecurityContextHolder<T> securityContextHolder) {
		this.securityContextHolder = securityContextHolder;
	}
}

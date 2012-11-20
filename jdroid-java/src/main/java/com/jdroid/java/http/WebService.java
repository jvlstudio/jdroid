package com.jdroid.java.http;

import java.util.Collection;
import org.apache.http.cookie.Cookie;
import com.jdroid.java.parser.Parser;

public interface WebService {
	
	/**
	 * @param <T>
	 * @param parser
	 * @return WebServiceResponse
	 */
	public <T> T execute(Parser parser);
	
	/**
	 * @param <T>
	 * @return WebServiceResponse
	 */
	public <T> T execute();
	
	/**
	 * @param name The header name.
	 * @param value The header value.
	 */
	public void addHeader(String name, String value);
	
	/**
	 * @param name The parameter name.
	 * @param value The parameter value.
	 */
	public void addQueryParameter(String name, Object value);
	
	/**
	 * @param name The parameter name.
	 * @param values The parameter values.
	 */
	public void addQueryParameter(String name, Collection<?> values);
	
	/**
	 * @param segment The segment name
	 */
	public void addUrlSegment(Object segment);
	
	/**
	 * @param connectionTimeout The connection timeout in milliseconds.
	 */
	public void setConnectionTimeout(Integer connectionTimeout);
	
	/**
	 * @param userAgent The user agent
	 */
	public void setUserAgent(String userAgent);
	
	/**
	 * @param cookie The {@link Cookie} to add
	 */
	public void addCookie(Cookie cookie);
	
	/**
	 * @param ssl
	 */
	public void setSsl(Boolean ssl);
	
}

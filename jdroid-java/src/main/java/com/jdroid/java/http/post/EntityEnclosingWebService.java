package com.jdroid.java.http.post;

import org.apache.http.HttpEntity;
import com.jdroid.java.http.WebService;

public interface EntityEnclosingWebService extends WebService {
	
	/**
	 * @param entity
	 */
	public void setEntity(HttpEntity entity);
	
	public void setEntity(String content);
	
}

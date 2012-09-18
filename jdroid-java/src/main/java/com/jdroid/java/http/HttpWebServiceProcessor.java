package com.jdroid.java.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;

/**
 * 
 * @author Maxi Rosson
 */
public interface HttpWebServiceProcessor {
	
	public void beforeExecute(WebService webService);
	
	public void afterExecute(WebService webService, HttpClient client, HttpResponse httpResponse);
	
}
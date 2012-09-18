package com.jdroid.java.http.put;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPut;
import com.jdroid.java.http.HttpClientFactory;
import com.jdroid.java.http.HttpEntityEnclosingWebService;
import com.jdroid.java.http.HttpWebServiceProcessor;

public class HttpPutWebService extends HttpEntityEnclosingWebService {
	
	public HttpPutWebService(HttpClientFactory httpClientFactory, String baseURL,
			HttpWebServiceProcessor... httpWebServiceProcessors) {
		super(httpClientFactory, baseURL, httpWebServiceProcessors);
	}
	
	/**
	 * @see com.jdroid.java.http.HttpWebService#getMethodName()
	 */
	@Override
	public String getMethodName() {
		return HttpPut.METHOD_NAME;
	}
	
	/**
	 * @see com.jdroid.java.http.HttpEntityEnclosingWebService#createHttpEntityEnclosingRequestBase(java.lang.String)
	 */
	@Override
	protected HttpEntityEnclosingRequestBase createHttpEntityEnclosingRequestBase(String uri) {
		return new HttpPut(uri);
	}
}

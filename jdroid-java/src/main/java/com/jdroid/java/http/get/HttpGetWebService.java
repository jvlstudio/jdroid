package com.jdroid.java.http.get;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import com.jdroid.java.http.HttpClientFactory;
import com.jdroid.java.http.HttpWebService;
import com.jdroid.java.http.HttpWebServiceProcessor;

public class HttpGetWebService extends HttpWebService {
	
	public HttpGetWebService(HttpClientFactory httpClientFactory, String baseURL,
			HttpWebServiceProcessor... httpWebServiceProcessors) {
		super(httpClientFactory, baseURL, httpWebServiceProcessors);
	}
	
	/**
	 * @see com.jdroid.java.http.HttpWebService#getMethodName()
	 */
	@Override
	public String getMethodName() {
		return HttpGet.METHOD_NAME;
	}
	
	/**
	 * @see com.jdroid.java.http.HttpWebService#createHttpUriRequest()
	 */
	@Override
	protected HttpUriRequest createHttpUriRequest(String protocol) {
		return new HttpGet(protocol + "://" + getBaseURL() + getUrlSegments() + makeStringParameters());
	}
}

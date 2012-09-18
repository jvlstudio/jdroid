package com.jdroid.java.http.post;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import com.jdroid.java.http.HttpClientFactory;
import com.jdroid.java.http.HttpEntityEnclosingWebService;
import com.jdroid.java.http.HttpWebServiceProcessor;

public class HttpPostWebService extends HttpEntityEnclosingWebService {
	
	public HttpPostWebService(HttpClientFactory httpClientFactory, String baseURL,
			HttpWebServiceProcessor... httpWebServiceProcessors) {
		super(httpClientFactory, baseURL, httpWebServiceProcessors);
	}
	
	/**
	 * @see com.jdroid.java.http.HttpWebService#getMethodName()
	 */
	@Override
	public String getMethodName() {
		return HttpPost.METHOD_NAME;
	}
	
	/**
	 * @see com.jdroid.java.http.HttpEntityEnclosingWebService#createHttpEntityEnclosingRequestBase(java.lang.String)
	 */
	@Override
	protected HttpEntityEnclosingRequestBase createHttpEntityEnclosingRequestBase(String uri) {
		return new HttpPost(uri);
	}
}

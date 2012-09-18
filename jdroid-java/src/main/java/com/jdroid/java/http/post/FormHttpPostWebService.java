package com.jdroid.java.http.post;

import java.io.UnsupportedEncodingException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import com.jdroid.java.exception.UnexpectedException;
import com.jdroid.java.http.HttpClientFactory;
import com.jdroid.java.http.HttpWebServiceProcessor;
import com.jdroid.java.utils.EncodingUtils;

/**
 * 
 * @author Maxi Rosson
 */
public class FormHttpPostWebService extends HttpPostWebService {
	
	public FormHttpPostWebService(HttpClientFactory httpClientFactory, String baseURL,
			HttpWebServiceProcessor... httpWebServiceProcessors) {
		super(httpClientFactory, baseURL, httpWebServiceProcessors);
	}
	
	/**
	 * @see com.jdroid.java.http.post.HttpPostWebService#addEntity(org.apache.http.client.methods.HttpEntityEnclosingRequestBase)
	 */
	@Override
	protected void addEntity(HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase) {
		try {
			httpEntityEnclosingRequestBase.setEntity(new UrlEncodedFormEntity(getQueryParameters(), EncodingUtils.UTF8));
		} catch (UnsupportedEncodingException e) {
			throw new UnexpectedException(e);
		}
	}
}

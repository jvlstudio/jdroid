package com.jdroid.android.cookie;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import com.jdroid.android.AbstractApplication;
import com.jdroid.java.http.HttpWebServiceProcessor;
import com.jdroid.java.http.WebService;

/**
 * 
 * @author Maxi Rosson
 */
public class CookieHttpWebServiceProcessor implements HttpWebServiceProcessor {
	
	/** The repository to handle the http client's cookies */
	private CookieRepository cookieRepository;
	
	private static final CookieHttpWebServiceProcessor INSTANCE = new CookieHttpWebServiceProcessor();
	
	public static CookieHttpWebServiceProcessor get() {
		return INSTANCE;
	}
	
	private CookieHttpWebServiceProcessor() {
		cookieRepository = AbstractApplication.getInstance(CookieRepository.class);
	}
	
	/**
	 * @see com.jdroid.java.http.HttpWebServiceProcessor#beforeExecute(com.jdroid.java.http.WebService)
	 */
	@Override
	public void beforeExecute(WebService webService) {
		// Add all the cookies to the http client
		for (Cookie cookie : cookieRepository.getAll()) {
			webService.addCookie(cookie);
		}
	}
	
	/**
	 * @see com.jdroid.java.http.HttpWebServiceProcessor#afterExecute(com.jdroid.java.http.WebService,
	 *      org.apache.http.client.HttpClient, org.apache.http.HttpResponse)
	 */
	@Override
	public void afterExecute(WebService webService, HttpClient client, HttpResponse httpResponse) {
		// Save all returned cookies into the repository.
		cookieRepository.addAll(DefaultHttpClient.class.cast(client).getCookieStore().getCookies());
	}
	
}

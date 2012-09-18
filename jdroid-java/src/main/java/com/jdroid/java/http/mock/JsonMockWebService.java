package com.jdroid.java.http.mock;

/**
 * 
 * @author Maxi Rosson
 */
public abstract class JsonMockWebService extends AbstractMockWebService {
	
	private final static String MOCKS_BASE_PATH = "mocks/json/";
	private final static String MOCKS_EXTENSION = ".json";
	
	public JsonMockWebService(Object... urlSegments) {
		super(urlSegments);
	}
	
	/**
	 * @see com.jdroid.java.http.mock.AbstractMockWebService#getMocksBasePath()
	 */
	@Override
	protected String getMocksBasePath() {
		return MOCKS_BASE_PATH;
	}
	
	/**
	 * @see com.jdroid.java.http.mock.AbstractMockWebService#getMocksExtension()
	 */
	@Override
	protected String getMocksExtension() {
		return MOCKS_EXTENSION;
	}
	
}

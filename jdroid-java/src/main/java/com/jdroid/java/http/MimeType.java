package com.jdroid.java.http;

/**
 * 
 * @author Maxi Rosson
 */
public enum MimeType {
	
	JSON("application/json"),
	PNG("image/png"),
	TEXT("text/plain");
	
	private String name;
	
	private MimeType(String name) {
		this.name = name;
	}
	
	/**
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return name;
	}
}

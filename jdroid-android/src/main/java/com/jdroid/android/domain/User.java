package com.jdroid.android.domain;

/**
 * 
 * @author Maxi Rosson
 */
// TODO Provisory. Find a better way to do this
public interface User {
	
	public Long getId();
	
	public String getUserName();
	
	public String getFullname();
	
	public String getEmail();
	
	public String getFirstName();
	
	public String getLastName();
	
	public String getUserToken();
	
	public FileContent getImage();
	
}

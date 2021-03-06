package com.jdroid.java.domain;

/**
 * Represent all those objects that could be identifiable by an ID
 * 
 */
public interface Identifiable {
	
	/**
	 * Gets the identification for this {@link Identifiable}
	 * 
	 * @return the id of this {@link Identifiable}
	 */
	public Long getId();
	
}

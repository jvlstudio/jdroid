package com.jdroid.javaweb.context;

import com.jdroid.javaweb.domain.Entity;

/**
 * Provides access to the {@link AbstractSecurityContext}
 * 
 * @param <T>
 */
public abstract class SecurityContextHolder<T extends Entity> {
	
	/**
	 * @return The {@link AbstractSecurityContext} instance
	 */
	public abstract AbstractSecurityContext<T> getContext();
	
}
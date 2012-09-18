package com.jdroid.javaweb.guava.function;

import java.io.Serializable;
import com.google.common.base.Function;
import com.jdroid.java.utils.ReflectionUtils;

/**
 * Transforms an object into one of its property
 * 
 * @param <F> The type of the parameter object
 * @param <T> The type of the result object
 * 
 */
public class PropertyFunction<F, T> implements Function<F, T>, Serializable {
	
	private String propertyName;
	
	/**
	 * @param propertyName The name of the object's property
	 */
	public PropertyFunction(String propertyName) {
		this.propertyName = propertyName;
	}
	
	/**
	 * @see com.google.common.base.Function#apply(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T apply(F from) {
		return (T)ReflectionUtils.getFieldValue(from, this.propertyName);
	}
}

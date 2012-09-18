package com.jdroid.javaweb.guava.function;

import java.io.Serializable;
import org.apache.commons.beanutils.NestedNullException;
import org.apache.commons.beanutils.PropertyUtils;
import com.google.common.base.Function;
import com.jdroid.java.exception.UnexpectedException;

/**
 * Transforms an object into one of its property
 * 
 * @param <F> The type of the parameter object
 * @param <T> The type of the result object
 * 
 */
public class NestedPropertyFunction<F, T> implements Function<F, T>, Serializable {
	
	private String propertyName;
	
	/**
	 * @param propertyName The name of the object's property
	 */
	public NestedPropertyFunction(String propertyName) {
		this.propertyName = propertyName;
	}
	
	/**
	 * @see com.google.common.base.Function#apply(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T apply(F from) {
		try {
			return (T)PropertyUtils.getNestedProperty(from, this.propertyName);
		} catch (NestedNullException exception) {
			// if any the nested objects is null we return null as value
			return null;
		} catch (Exception ex) {
			throw new UnexpectedException("Error getting the property: '" + this.propertyName + "' of: " + from);
		}
	}
}

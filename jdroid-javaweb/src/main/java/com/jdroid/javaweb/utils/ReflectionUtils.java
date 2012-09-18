package com.jdroid.javaweb.utils;

import org.apache.commons.beanutils.NestedNullException;
import org.apache.commons.beanutils.PropertyUtils;
import com.jdroid.java.exception.UnexpectedException;

/**
 * Reflection related utilities
 */
public abstract class ReflectionUtils extends com.jdroid.java.utils.ReflectionUtils {
	
	/**
	 * @param <T> The type of the result object
	 * @param from The object where invoke the property
	 * @param propertyName The name of the property to invoke
	 * @return The property value
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getPropertyValue(Object from, String propertyName) {
		try {
			return (T)PropertyUtils.getNestedProperty(from, propertyName);
		} catch (NestedNullException exception) {
			// if any the nested objects is null we return null as value
			return null;
		} catch (Exception ex) {
			throw new UnexpectedException("Error getting the property: '" + propertyName + "' of: " + from);
		}
	}
	
}

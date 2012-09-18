package com.jdroid.javaweb.guava.predicate;

import java.io.Serializable;
import java.util.Collection;

import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.jdroid.javaweb.guava.function.PropertyFunction;

/**
 * Predicates that verifies if the input value is contained by the property represent by the propertyName or viceversa.
 * 
 * @param <T> The object type
 * 
 */
public class ContainsPropertyPredicate<T> implements Predicate<T>, Serializable {
	
	private Object value;
	private PropertyFunction<T, Object> propertyFunction;
	private boolean valueIsCollection;
	
	/**
	 * @param propertyName The propertyName The property name of the main object to get the property value
	 * @param value The value or collection of values to evaluate the contains
	 */
	public ContainsPropertyPredicate(String propertyName, Object value) {
		this.propertyFunction = new PropertyFunction<T, Object>(propertyName);
		this.value = value;
		this.valueIsCollection = Collection.class.isInstance(this.value);
	}
	
	/**
	 * @see com.google.common.base.Predicate#apply(java.lang.Object)
	 */
	@Override
	public boolean apply(T input) {
		Object object = this.propertyFunction.apply(input);
		if (valueIsCollection) {
			return contains(Collection.class.cast(this.value), object);
		} else if (Collection.class.isInstance(object)) {
			return contains(Collection.class.cast(object), this.value);
		}
		return Objects.equal(this.value, object);
	}
	
	private boolean contains(Collection<?> collection, Object object) {
		return Iterables.contains(collection, object);
	}
	
}

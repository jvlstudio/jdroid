package com.jdroid.javaweb.marshaller;

import org.hibernate.Hibernate;
import com.jdroid.java.marshaller.MarshallerProvider.MarshallerTypeEvaluator;

/**
 * 
 * @author Maxi Rosson
 */
public class HibernateMarshallerTypeEvaluator implements MarshallerTypeEvaluator {
	
	/**
	 * @see com.jdroid.java.marshaller.MarshallerProvider.MarshallerTypeEvaluator#evaluate(java.lang.Object)
	 */
	@Override
	public Class<?> evaluate(Object marshallerType) {
		return Hibernate.getClass(marshallerType);
	}
}

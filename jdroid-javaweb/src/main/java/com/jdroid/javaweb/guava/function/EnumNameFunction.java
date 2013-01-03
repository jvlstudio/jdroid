package com.jdroid.javaweb.guava.function;

import java.io.Serializable;
import com.google.common.base.Function;

/**
 * Transforms an enum in its name
 * 
 * @param <E> The Enum type
 * 
 */
public class EnumNameFunction<E extends Enum<E>> implements Function<E, String>, Serializable {
	
	/**
	 * @see com.google.common.base.Function#apply(java.lang.Object)
	 */
	@Override
	public String apply(E from) {
		return from.name();
	}
}

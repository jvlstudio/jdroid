package com.jdroid.javaweb.guava.function;

import com.google.common.base.Function;

public class StringToLongFunction implements Function<String, Long> {
	
	/**
	 * @see com.google.common.base.Function#apply(java.lang.Object)
	 */
	@Override
	public Long apply(String input) {
		return Long.parseLong(input);
	}
}

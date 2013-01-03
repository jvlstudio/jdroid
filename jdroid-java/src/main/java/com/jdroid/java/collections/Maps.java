package com.jdroid.java.collections;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * 
 * @author Maxi Rosson
 */
public class Maps {
	
	/**
	 * Creates a <i>mutable</i>, empty {@code HashMap} instance.
	 * 
	 * @return a new, empty {@code HashMap}
	 */
	public static <K, V> HashMap<K, V> newHashMap() {
		return new HashMap<K, V>();
	}
	
	/**
	 * Creates a <i>mutable</i>, empty, insertion-ordered {@code LinkedHashMap} instance.
	 * 
	 * @return a new, empty {@code LinkedHashMap}
	 */
	public static <K, V> LinkedHashMap<K, V> newLinkedHashMap() {
		return new LinkedHashMap<K, V>();
	}
}

package com.jdroid.android.intent;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.Map;
import com.jdroid.java.collections.Maps;

/**
 * 
 * @author Maxi Rosson
 */
public class IntentContext implements Serializable {
	
	private static final String DEFAULT_KEY = "defaultKey";
	
	private static Map<String, WeakReference<Object>> data = Maps.newHashMap();
	
	/**
	 * @param value The value to add
	 */
	public static void addValue(Object value) {
		addValue(DEFAULT_KEY, value);
	}
	
	/**
	 * @param key The data key
	 * @param value The value to add
	 */
	public static void addValue(String key, Object value) {
		data.put(key, new WeakReference<Object>(value));
	}
	
	/**
	 * @param key The data key
	 * @return the data and remove it from the context, so you shouldn't invoke this method twice
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getValue(String key) {
		WeakReference<Object> reference = data.get(key);
		Object value = null;
		if (reference != null) {
			value = reference.get();
			data.remove(key);
		}
		return (T)value;
	}
	
	/**
	 * @return the data and remove it from the context, so you shouldn't invoke this method twice
	 */
	public static <T> T getValue() {
		return getValue(DEFAULT_KEY);
	}
}

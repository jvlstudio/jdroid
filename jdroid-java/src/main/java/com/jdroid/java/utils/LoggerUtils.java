package com.jdroid.java.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Maxi Rosson
 */
public class LoggerUtils {
	
	public static Logger getLogger(Object name) {
		return LoggerFactory.getLogger(name.getClass());
	}
	
	public static Logger getLogger(Class<?> clazz) {
		return LoggerFactory.getLogger(clazz.getSimpleName());
	}
	
}

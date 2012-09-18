package com.jdroid.java.parser;

import java.io.InputStream;

public interface Parser {
	
	/**
	 * Parse the inputStream
	 * 
	 * @param inputStream The inputStream to parse
	 * @return The parser response object
	 */
	public Object parse(InputStream inputStream);
	
	/**
	 * Parse the String
	 * 
	 * @param input The input to parse
	 * @return The parser response object
	 */
	public Object parse(String input);
	
}

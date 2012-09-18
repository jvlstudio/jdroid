package com.jdroid.java.parser.plain;

import java.io.InputStream;
import org.slf4j.Logger;
import com.jdroid.java.parser.Parser;
import com.jdroid.java.utils.FileUtils;
import com.jdroid.java.utils.LoggerUtils;

/**
 * 
 * @author Maxi Rosson
 */
public abstract class PlainTextParser implements Parser {
	
	private static final Logger LOGGER = LoggerUtils.getLogger(PlainTextParser.class);
	
	/**
	 * @see com.jdroid.java.parser.Parser#parse(java.io.InputStream)
	 */
	@Override
	public Object parse(InputStream inputStream) {
		LOGGER.debug("Parsing started.");
		try {
			// Read the plain text response
			String result = FileUtils.toString(inputStream);
			LOGGER.debug(result);
			
			// Parse the plain text
			return parse(result);
		} finally {
			LOGGER.debug("Parsing finished.");
		}
	}
	
	/**
	 * @see com.jdroid.java.parser.Parser#parse(java.lang.String)
	 */
	@Override
	public abstract Object parse(String plainText);
}
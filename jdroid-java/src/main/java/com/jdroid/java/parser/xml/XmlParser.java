package com.jdroid.java.parser.xml;

import java.io.IOException;
import java.io.InputStream;
import org.slf4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import com.jdroid.java.exception.UnexpectedException;
import com.jdroid.java.parser.Parser;
import com.jdroid.java.utils.EncodingUtils;
import com.jdroid.java.utils.FileUtils;
import com.jdroid.java.utils.LoggerUtils;
import com.jdroid.java.utils.NumberUtils;
import com.sun.org.apache.xerces.internal.parsers.SAXParser;

/**
 * XML input streams parser
 * 
 * @author Maxi Rosson
 */
public abstract class XmlParser extends DefaultHandler implements Parser {
	
	private static final Logger LOGGER = LoggerUtils.getLogger(XmlParser.class);
	
	private StringBuilder builder = new StringBuilder();
	
	/**
	 * @see com.jdroid.java.parser.Parser#parse(java.lang.String)
	 */
	@Override
	public Object parse(String input) {
		return null;
	}
	
	/**
	 * @see com.jdroid.java.parser.Parser#parse(java.io.InputStream)
	 */
	@Override
	public Object parse(InputStream inputStream) {
		
		LOGGER.debug("Parsing started.");
		try {
			if (LOGGER.isDebugEnabled()) {
				inputStream = FileUtils.copy(inputStream);
				LOGGER.debug(FileUtils.toString(inputStream));
				inputStream.reset();
			}
			
			// Parse the xml
			XMLReader reader = new SAXParser();
			reader.setContentHandler(this);
			InputSource source = new InputSource(inputStream);
			source.setEncoding(EncodingUtils.UTF8);
			reader.parse(source);
			
			return getResponse();
		} catch (IOException e) {
			throw new UnexpectedException(e);
		} catch (SAXException e) {
			throw new UnexpectedException(e);
		} finally {
			LOGGER.debug("Parsing finished.");
		}
	}
	
	/**
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String,
	 *      org.xml.sax.Attributes)
	 */
	@Override
	public final void startElement(String uri, String localName, String qName, Attributes attributes) {
		try {
			super.startElement(uri, localName, qName, attributes);
			this.onStartElement(localName, attributes);
		} catch (SAXException e) {
			throw new UnexpectedException(e);
		}
	}
	
	/**
	 * Method called at the start of every element of the XML.
	 * 
	 * @param localName The name of the tag.
	 * @param attributes The attributes of the tag.
	 */
	protected abstract void onStartElement(String localName, Attributes attributes);
	
	/**
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public final void endElement(String uri, String localName, String qName) {
		try {
			super.endElement(uri, localName, qName);
			this.onEndElement(localName, builder.toString().trim());
			builder.setLength(0);
		} catch (SAXException e) {
			throw new UnexpectedException(e);
		}
	}
	
	/**
	 * Method called at the end of every element of the XML.
	 * 
	 * @param localName The name of the tag.
	 * @param content The content of the tag.
	 */
	protected abstract void onEndElement(String localName, String content);
	
	/**
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		builder.append(ch, start, length);
	}
	
	/**
	 * @return The parser response
	 */
	protected abstract Object getResponse();
	
	/**
	 * @param attributes The {@link Attributes}
	 * @param xmlAttr The attribute used to retrieve the value
	 * @param value The value to compare
	 * @return Whether the attribute has the received value or not
	 */
	protected Boolean hasAttribute(Attributes attributes, XMLAttr xmlAttr, String value) {
		return value.equals(getStringValue(attributes, xmlAttr));
	}
	
	/**
	 * @param attributes The {@link Attributes}
	 * @param xmlAttr The attribute used to retrieve the value
	 * @return The {@link Integer} value of the attribute
	 */
	protected Integer getIntegerValue(Attributes attributes, XMLAttr xmlAttr) {
		String value = attributes.getValue(xmlAttr.getName());
		return NumberUtils.getInteger(value);
	}
	
	/**
	 * @param attributes The {@link Attributes}
	 * @param xmlAttr The attribute used to retrieve the value
	 * @return The {@link Long} value of the attribute
	 */
	protected Long getLongValue(Attributes attributes, XMLAttr xmlAttr) {
		String value = attributes.getValue(xmlAttr.getName());
		return NumberUtils.getLong(value);
	}
	
	/**
	 * @param attributes The {@link Attributes}
	 * @param xmlAttr The attribute used to retrieve the value
	 * @return The {@link Double} value of the attribute
	 */
	protected Double getDoubleValue(Attributes attributes, XMLAttr xmlAttr) {
		String value = attributes.getValue(xmlAttr.getName());
		return NumberUtils.getDouble(value);
	}
	
	/**
	 * @param attributes The {@link Attributes}
	 * @param xmlAttr The attribute used to retrieve the value
	 * @return The {@link Float} value of the attribute
	 */
	protected Float getFloatValue(Attributes attributes, XMLAttr xmlAttr) {
		String value = attributes.getValue(xmlAttr.getName());
		return NumberUtils.getFloat(value);
	}
	
	/**
	 * @param attributes The {@link Attributes}
	 * @param xmlAttr The attribute used to retrieve the value
	 * @return The {@link Boolean} value of the attribute
	 */
	protected Boolean getBooleanFromNumberValue(Attributes attributes, XMLAttr xmlAttr) {
		String value = attributes.getValue(xmlAttr.getName());
		return NumberUtils.getBooleanFromNumber(value);
	}
	
	/**
	 * @param attributes The {@link Attributes}
	 * @param xmlAttr The attribute used to retrieve the value
	 * @return The {@link String} value of the attribute
	 */
	protected String getStringValue(Attributes attributes, XMLAttr xmlAttr) {
		return attributes.getValue(xmlAttr.getName());
	}
}

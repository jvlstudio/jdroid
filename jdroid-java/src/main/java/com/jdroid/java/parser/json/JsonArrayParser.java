package com.jdroid.java.parser.json;

import org.json.JSONException;

/**
 * 
 * @author Maxi Rosson
 */
public class JsonArrayParser extends JsonParser<JsonArrayWrapper> {
	
	private JsonParser<JsonObjectWrapper> parser;
	
	public JsonArrayParser(JsonParser<JsonObjectWrapper> parser) {
		this.parser = parser;
	}
	
	/**
	 * @see com.jdroid.java.parser.json.JsonParser#parse(java.lang.Object)
	 */
	@Override
	public Object parse(JsonArrayWrapper json) throws JSONException {
		return parseList(json, parser);
	}
}
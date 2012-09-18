package com.jdroid.java.json;

import org.json.JSONException;
import org.json.JSONStringer;
import com.jdroid.java.exception.UnexpectedException;

/**
 * 
 * @author Maxi Rosson
 */
public class JSONBuilder {
	
	private JSONStringer jsonStringer = new JSONStringer();
	
	public JSONBuilder startObject() {
		try {
			jsonStringer.object();
			return this;
		} catch (JSONException e) {
			throw new UnexpectedException(e);
		}
	}
	
	public JSONBuilder endObject() {
		try {
			jsonStringer.endObject();
			return this;
		} catch (JSONException e) {
			throw new UnexpectedException(e);
		}
	}
	
	public JSONBuilder startArray() {
		try {
			jsonStringer.array();
			return this;
		} catch (JSONException e) {
			throw new UnexpectedException(e);
		}
	}
	
	public JSONBuilder startArray(String key) {
		try {
			jsonStringer.key(key);
			jsonStringer.array();
			return this;
		} catch (JSONException e) {
			throw new UnexpectedException(e);
		}
	}
	
	public JSONBuilder endArray() {
		try {
			jsonStringer.endArray();
			return this;
		} catch (JSONException e) {
			throw new UnexpectedException(e);
		}
	}
	
	public JSONBuilder add(String key, int value) {
		return add(key, (Integer)value);
	}
	
	public JSONBuilder add(String key, double value) {
		return add(key, (Double)value);
	}
	
	public JSONBuilder add(String key, long value) {
		return add(key, (Long)value);
	}
	
	public JSONBuilder add(String key, Object value) {
		try {
			jsonStringer.key(key);
			jsonStringer.value(value);
			return this;
		} catch (JSONException e) {
			throw new UnexpectedException(e);
		}
	}
	
	public JSONBuilder addIfExists(String key, Object value) {
		if (value != null) {
			add(key, value);
		}
		return this;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return jsonStringer.toString();
	}
}

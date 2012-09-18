package com.jdroid.javaweb.search;

import java.util.Collection;
import java.util.Map;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class Filter {
	
	private Pager pager;
	private Sorting sorting;
	private Map<FilterKey, Object> values = Maps.newHashMap();
	
	public Filter() {
		this(null, null);
	}
	
	public Filter(Integer page, Integer pageSize) {
		pager = new Pager(page, pageSize);
	}
	
	public void addValue(FilterKey key, Object value) {
		values.put(key, value);
	}
	
	public void addValues(FilterKey key, Object... value) {
		values.put(key, Lists.newArrayList(value));
	}
	
	public Object getValue(FilterKey key) {
		return getValue(key, null);
	}
	
	public String getStringValue(FilterKey key) {
		return (String)getValue(key, null);
	}
	
	public Boolean getBooleanValue(FilterKey key) {
		return (Boolean)getValue(key, null);
	}
	
	public Integer getIntegerValue(FilterKey key) {
		return (Integer)getValue(key, null);
	}
	
	public Long getLongValue(FilterKey key) {
		return (Long)getValue(key, null);
	}
	
	@SuppressWarnings("unchecked")
	public <T> Collection<T> getCollectionValue(FilterKey key) {
		return (Collection<T>)getValue(key, null);
	}
	
	public Object getValue(FilterKey key, Object defaultValue) {
		Object object = values.get(key);
		return object != null ? object : defaultValue;
	}
	
	/**
	 * @return the pager
	 */
	public Pager getPager() {
		return pager;
	}
	
	public Sorting getSorting() {
		return sorting;
	}
	
	public void setSorting(Sorting sorting) {
		this.sorting = sorting;
	}
	
}

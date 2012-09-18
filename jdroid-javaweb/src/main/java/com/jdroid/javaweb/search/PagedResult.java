package com.jdroid.javaweb.search;

import java.util.List;
import com.google.common.collect.Lists;

/**
 * 
 * @param <T> Type of the data to return
 */
public class PagedResult<T> {
	
	private List<T> data;
	private Boolean lastPage;
	
	public PagedResult() {
		this(Lists.<T>newArrayList());
	}
	
	public PagedResult(List<T> data) {
		this(data, true);
	}
	
	public PagedResult(List<T> data, Boolean lastPage) {
		this.data = data;
		this.lastPage = lastPage;
	}
	
	/**
	 * @return the data
	 */
	public List<T> getData() {
		return data;
	}
	
	/**
	 * @return the lastPage
	 */
	public Boolean isLastPage() {
		return lastPage;
	}
}

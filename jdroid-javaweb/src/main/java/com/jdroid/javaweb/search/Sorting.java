package com.jdroid.javaweb.search;

public class Sorting {
	
	private SortingKey sortingKey;
	private Boolean ascending;
	
	/**
	 * @param sortingKey
	 * @param ascending
	 */
	public Sorting(SortingKey sortingKey, Boolean ascending) {
		this.sortingKey = sortingKey;
		this.ascending = ascending;
	}
	
	public Sorting(SortingKey sortingKey) {
		this(sortingKey, true);
	}
	
	public Boolean isAscending() {
		return ascending;
	}
	
	public SortingKey getSortingKey() {
		return sortingKey;
	}
	
}

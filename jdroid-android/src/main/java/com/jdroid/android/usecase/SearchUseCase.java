package com.jdroid.android.usecase;

import java.util.Set;
import com.jdroid.android.search.SearchResult;
import com.jdroid.java.collections.Sets;

/**
 * 
 * @author Maxi Rosson
 * @param <T> item to search for
 */
public abstract class SearchUseCase<T> extends DefaultAbstractUseCase {
	
	private String searchValue;
	private SearchResult<T> searchResult;
	private Set<T> selectedItems = Sets.newHashSet();
	
	/**
	 * @see com.jdroid.android.usecase.DefaultAbstractUseCase#doExecute()
	 */
	@Override
	protected void doExecute() {
		searchResult = doSearch(searchValue);
	}
	
	protected abstract SearchResult<T> doSearch(String searchValue);
	
	/**
	 * @return the searchResult
	 */
	public SearchResult<T> getSearchResult() {
		return searchResult;
	}
	
	/**
	 * @param searchValue the searchValue to set
	 */
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	
	public Set<T> getSelectedItems() {
		return selectedItems;
	}
	
	public void setSelectedItems(Set<T> selectedItems) {
		this.selectedItems = selectedItems;
	}
}

package com.jdroid.android.usecase;

/**
 * 
 * @param <T> item to search for
 * @param <A> The Api Service type
 * 
 * @author Maxi Rosson
 */
public abstract class SearchApiUseCase<T, A> extends SearchUseCase<T> {
	
	private A apiService;
	
	public SearchApiUseCase(A apiService) {
		this.apiService = apiService;
	}
	
	public A getApiService() {
		return apiService;
	}
}

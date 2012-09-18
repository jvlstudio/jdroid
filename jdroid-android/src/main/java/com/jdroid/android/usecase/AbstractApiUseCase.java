package com.jdroid.android.usecase;


/**
 * @param <A> The Api Service type
 * 
 * @author Maxi Rosson
 */
public abstract class AbstractApiUseCase<A> extends DefaultAbstractUseCase {
	
	private A apiService;
	
	public AbstractApiUseCase(A apiService) {
		this.apiService = apiService;
	}
	
	public A getApiService() {
		return apiService;
	}
	
}

package com.jdroid.android.usecase.listener;

/**
 * Default Use Case Listener
 * 
 * @author Maxi Rosson
 */
public interface DefaultUseCaseListener {
	
	/**
	 * Called before the use case starts
	 */
	public void onStartUseCase();
	
	/**
	 * Called after the use case starts to report an update status if necessary
	 */
	public void onUpdateUseCase();
	
	/**
	 * Called after the use case fails
	 * 
	 * @param runtimeException The {@link RuntimeException} with the error
	 */
	public void onFinishFailedUseCase(RuntimeException runtimeException);
	
	/**
	 * Called when the use case finishes successfully
	 */
	public void onFinishUseCase();
	
	/**
	 * Called when the use case finishes successfully but was cancelled during its execution
	 */
	public void onFinishCanceledUseCase();
	
}

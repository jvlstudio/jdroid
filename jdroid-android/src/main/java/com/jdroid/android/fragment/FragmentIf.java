package com.jdroid.android.fragment;

import android.app.Activity;
import android.view.View;
import com.google.ads.AdSize;
import com.jdroid.android.dialog.LoadingDialog;
import com.jdroid.android.domain.User;
import com.jdroid.android.usecase.DefaultUseCase;
import com.jdroid.android.usecase.listener.DefaultUseCaseListener;

/**
 * 
 * @author Maxi Rosson
 */
public interface FragmentIf extends DefaultUseCaseListener {
	
	/**
	 * Finds a view that was identified by the id attribute from the XML that was processed in {@link Activity#onCreate}
	 * 
	 * @param id The id to search for.
	 * @param <V> The {@link View} class
	 * 
	 * @return The view if found or null otherwise.
	 */
	public <V extends View> V findView(int id);
	
	/**
	 * Inflate a new view hierarchy from the specified xml resource.
	 * 
	 * @param resource ID for an XML layout resource to load
	 * @return The root View of the inflated XML file.
	 */
	public View inflate(int resource);
	
	/**
	 * Runs the specified action on the UI thread. If the current thread is the UI thread, then the action is executed
	 * immediately. If the current thread is not the UI thread, the action is posted to the event queue of the UI
	 * thread.
	 * 
	 * If the current {@link Activity} is not equals to this, then no action is executed
	 * 
	 * @param runnable the action to run on the UI thread
	 */
	public void executeOnUIThread(Runnable runnable);
	
	/**
	 * Show the {@link LoadingDialog} in the current Thread. This dialog can be cancelled
	 */
	public void showLoading();
	
	/**
	 * Show the {@link LoadingDialog} in the current Thread. This dialog can be cancelled
	 * 
	 * @param loadingResId The loading resource id
	 */
	public void showLoading(Integer loadingResId);
	
	/**
	 * Show the {@link LoadingDialog} in the current Thread
	 * 
	 * @param cancelable Whether the {@link LoadingDialog} can be cancelled or not
	 */
	public void showLoading(Boolean cancelable);
	
	/**
	 * Show the {@link LoadingDialog} in the current Thread
	 * 
	 * @param cancelable Whether the {@link LoadingDialog} can be cancelled or not
	 * @param loadingResId The loading resource id
	 */
	public void showLoading(Boolean cancelable, Integer loadingResId);
	
	/**
	 * Show the {@link LoadingDialog} in the UI Thread. This dialog can be cancelled
	 */
	public void showLoadingOnUIThread();
	
	/**
	 * Show the {@link LoadingDialog} in the UI Thread. This dialog can be cancelled
	 * 
	 * @param loadingResId The loading resource id
	 */
	public void showLoadingOnUIThread(Integer loadingResId);
	
	/**
	 * Show the {@link LoadingDialog} in the UI Thread
	 * 
	 * @param cancelable Whether the {@link LoadingDialog} can be cancelled or not
	 */
	public void showLoadingOnUIThread(Boolean cancelable);
	
	/**
	 * Show the {@link LoadingDialog} in the UI Thread
	 * 
	 * @param cancelable Whether the {@link LoadingDialog} can be cancelled or not
	 * @param loadingResId The loading resource id
	 */
	public void showLoadingOnUIThread(Boolean cancelable, Integer loadingResId);
	
	/**
	 * Dismiss the {@link LoadingDialog} in the current Thread
	 */
	public void dismissLoading();
	
	/**
	 * Dismiss the {@link LoadingDialog} in the UI Thread
	 */
	public void dismissLoadingOnUIThread();
	
	/**
	 * @param clazz The {@link Class}
	 * @param <I> The instance type
	 * @return An instance of the clazz
	 */
	public <I> I getInstance(Class<I> clazz);
	
	/**
	 * @param key The key of the intent extra
	 * @param <E> The instance type
	 * @return the entry with the given key as an object.
	 */
	public <E> E getExtra(String key);
	
	public void executeUseCase(DefaultUseCase<?> useCase);
	
	public User getUser();
	
	public AdSize getAdSize();
}

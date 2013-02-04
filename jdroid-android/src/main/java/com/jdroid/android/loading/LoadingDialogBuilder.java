package com.jdroid.android.loading;

import android.app.Activity;
import android.app.Dialog;
import com.jdroid.android.AbstractApplication;

/**
 * 
 * @author Maxi Rosson
 */
public abstract class LoadingDialogBuilder {
	
	private Boolean cancelable = AbstractApplication.get().isLoadingCancelable();
	private Integer loadingResId = null;
	
	public abstract Dialog build(Activity activity);
	
	public LoadingDialogBuilder setCancelable(Boolean cancelable) {
		this.cancelable = cancelable;
		return this;
	}
	
	public Boolean isCancelable() {
		return cancelable;
	}
	
	public LoadingDialogBuilder setLoadingResId(int loadingResId) {
		this.loadingResId = loadingResId;
		return this;
	}
	
	public Integer getLoadingResId() {
		return loadingResId;
	}
}
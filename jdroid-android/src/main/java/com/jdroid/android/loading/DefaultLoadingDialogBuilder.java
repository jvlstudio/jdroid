package com.jdroid.android.loading;

import android.app.Activity;
import android.app.Dialog;

/**
 * 
 * @author Maxi Rosson
 */
public class DefaultLoadingDialogBuilder extends LoadingDialogBuilder {
	
	@Override
	public Dialog build(Activity activity) {
		LoadingDialog loadingDialog = new LoadingDialog(activity);
		if (getLoadingResId() != null) {
			loadingDialog.setLoadingText(getLoadingResId());
		}
		loadingDialog.setCancelable(isCancelable());
		return loadingDialog;
	}
}
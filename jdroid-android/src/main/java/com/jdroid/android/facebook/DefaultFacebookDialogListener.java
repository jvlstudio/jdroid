package com.jdroid.android.facebook;

import android.os.Bundle;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;
import com.jdroid.android.AbstractApplication;
import com.jdroid.android.exception.CommonErrorCode;

/**
 * 
 * @author Estefania Caravatti
 */
public class DefaultFacebookDialogListener implements DialogListener {
	
	/**
	 * @see com.facebook.android.Facebook.DialogListener#onComplete(android.os.Bundle)
	 */
	@Override
	public void onComplete(Bundle values) {
		// Nothing by default.
	}
	
	/**
	 * @see com.facebook.android.Facebook.DialogListener#onFacebookError(com.facebook.android.FacebookError)
	 */
	@Override
	public void onFacebookError(FacebookError e) {
		AbstractApplication.get().getExceptionHandler().handleException(Thread.currentThread(),
			CommonErrorCode.FACEBOOK_ERROR.newApplicationException(e));
	}
	
	/**
	 * @see com.facebook.android.Facebook.DialogListener#onError(com.facebook.android.DialogError)
	 */
	@Override
	public void onError(DialogError e) {
		AbstractApplication.get().getExceptionHandler().handleException(Thread.currentThread(),
			CommonErrorCode.FACEBOOK_ERROR.newApplicationException(e));
	}
	
	/**
	 * @see com.facebook.android.Facebook.DialogListener#onCancel()
	 */
	@Override
	public void onCancel() {
		// Nothing by default.
	}
	
}

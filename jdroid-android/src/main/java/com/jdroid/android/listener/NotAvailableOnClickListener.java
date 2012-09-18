package com.jdroid.android.listener;

import android.view.View;
import android.view.View.OnClickListener;
import com.jdroid.android.utils.ToastUtils;

/**
 * 
 * @author Maxi Rosson
 */
public class NotAvailableOnClickListener implements OnClickListener {
	
	/**
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		ToastUtils.showNotAvailableToast();
	}
	
}
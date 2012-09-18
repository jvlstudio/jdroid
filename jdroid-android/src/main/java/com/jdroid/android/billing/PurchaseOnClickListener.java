package com.jdroid.android.billing;

import android.view.View;
import android.view.View.OnClickListener;
import com.jdroid.android.R;
import com.jdroid.android.utils.ToastUtils;

/**
 * 
 * @author Maxi Rosson
 */
public class PurchaseOnClickListener implements OnClickListener {
	
	private BillingManager billingManager;
	private Purchasable purchasable;
	
	public PurchaseOnClickListener(BillingManager billingManager, Purchasable purchasable) {
		this.billingManager = billingManager;
		this.purchasable = purchasable;
	}
	
	protected void onBeforeOnClick() {
		// Do nothing by default
	}
	
	/**
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		onBeforeOnClick();
		if (!billingManager.requestPurchase(purchasable, null)) {
			ToastUtils.showToast(R.string.googlePlayConnectionError);
		}
	}
}

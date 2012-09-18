package com.jdroid.android.billing;

import android.app.Activity;
import com.jdroid.android.billing.BillingManagerImpl.RequestPurchase;
import com.jdroid.android.billing.BillingManagerImpl.RestoreTransactions;

/**
 * An interface for observing changes related to purchases.
 */
public interface PurchaseObserver {
	
	/**
	 * This is called when we receive a response code from the Google Play app for a RequestPurchase request that we
	 * made. This is NOT used for any purchase state changes. The order was sent successfully to the server.
	 * 
	 * @param request
	 */
	public void onRequestPurchaseOk(RequestPurchase request);
	
	/**
	 * This is called when we receive a response code from the Google Play app for a RequestPurchase request that we
	 * made. This is NOT used for any purchase state changes. The user didn't buy the item.
	 * 
	 * @param request
	 */
	public void onRequestPurchaseCanceledByUser(RequestPurchase request);
	
	/**
	 * This is called when we receive a response code from the Google Play app for a RequestPurchase request that we
	 * made. This is NOT used for any purchase state changes. We couldn't connect to the the Google Play server (for
	 * example if the data connection is down).
	 * 
	 * @param request
	 */
	public void onRequestPurchaseServiceUnavailable(RequestPurchase request);
	
	/**
	 * This is called when we receive a response code from the Google Play app for a RequestPurchase request that we
	 * made. This is NOT used for any purchase state changes. In-app billing is not supported yet.
	 * 
	 * @param request
	 */
	public void onRequestPurchaseBillingUnavailable(RequestPurchase request);
	
	/**
	 * This is called when we receive a response code from the Google Play app for a RequestPurchase request that we
	 * made. This is NOT used for any purchase state changes. The item this app offered for sale does not exist (or is
	 * not published) in the server-side catalog.
	 * 
	 * @param request
	 */
	public void onRequestPurchaseItemUnavailable(RequestPurchase request);
	
	/**
	 * This is called when we receive a response code from the Google Play app for a RequestPurchase request that we
	 * made. This is NOT used for any purchase state changes. Other errors (such as a server error).
	 * 
	 * @param request
	 */
	public void onRequestPurchaseError(RequestPurchase request);
	
	/**
	 * This is called when we receive a response code from the Google Play app for a RestoreTransactions request that we
	 * made. The request was successfully sent to the server.
	 * 
	 * @param request
	 */
	public void onRestoreTransactionsResponseOk(RestoreTransactions request);
	
	/**
	 * This is called when we receive a response code from the Google Play app for a RestoreTransactions request that we
	 * made. There was an error when sending the request to the server
	 * 
	 * @param request
	 */
	public void onRestoreTransactionsResponseError(RestoreTransactions request);
	
	public Activity getActivity();
	
}

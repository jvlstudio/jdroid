package com.jdroid.android.billing;

/**
 * 
 * @author Maxi Rosson
 */
public interface BillingListener {
	
	/**
	 * This is the callback that is invoked when the Google Play app responds to the
	 * {@link BillingManager#checkBillingSupported()} request and in-app billing is supported.
	 * 
	 */
	public void onBillingSupported();
	
	/**
	 * This is the callback that is invoked when the Google Play app responds to the
	 * {@link BillingManager#checkBillingSupported()} request and in-app billing is not supported.
	 * 
	 */
	public void onBillingNotSupported();
	
	/**
	 * This is the callback that is invoked when the Google Play app responds to the
	 * {@link BillingManager#checkBillingSupported()} request and there was an error connecting with the Google Play
	 * app.
	 */
	public void onGooglePlayConnectionError();
	
	/**
	 * This is the callback that is invoked when an item is purchased. It is the callback invoked in response to calling
	 * {@link BillingManager#requestPurchase(Purchasable, String)}. It may also be invoked asynchronously when a
	 * purchase is made on another device (if the purchase was for a Market-managed item).
	 * 
	 * @param order
	 */
	public void onPurchased(PurchaseOrder order);
	
	/**
	 * This is the callback that is invoked when an item is refunded. It is the callback invoked in response to calling
	 * {@link BillingManager#requestPurchase(Purchasable, String)}. It may also be invoked asynchronously when the
	 * purchase was refunded.
	 * 
	 * @param order
	 */
	public void onRefunded(PurchaseOrder order);
	
	/**
	 * This is the callback that is invoked when an item is canceled. It is the callback invoked in response to calling
	 * {@link BillingManager#requestPurchase(Purchasable, String)}. It may also be invoked asynchronously when the
	 * charge was canceled.
	 * 
	 * @param order
	 */
	public void onCanceled(PurchaseOrder order);
	
}

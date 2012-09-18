package com.jdroid.android.billing;

import android.content.Context;

/**
 * 
 * @author Maxi Rosson
 */
public interface BillingManager {
	
	/**
	 * Checks if in-app billing is supported.
	 * 
	 * @return false if there was an error connecting the Google Play app
	 */
	public boolean checkBillingSupported();
	
	/**
	 * Requests transaction information for all managed items. Call this only when the application is first installed or
	 * after a database wipe. Do NOT call this every time the application starts up.
	 * 
	 * @return false if there was an error connecting to the Google Play app
	 */
	public boolean restoreTransactions();
	
	/**
	 * Confirms receipt of a purchase state change. Each {@code notifyId} is an opaque identifier that came from the
	 * server. This method sends those identifiers back to the MarketBillingService, which ACKs them to the server.
	 * Returns false if there was an error trying to connect to the MarketBillingService.
	 * 
	 * @param notifyIds a list of opaque identifiers associated with purchase state changes.
	 * @return false if there was an error connecting to the Google Play app
	 */
	public boolean confirmNotifications(String... notifyIds);
	
	/**
	 * Requests that the given item be offered to the user for purchase. When the purchase succeeds (or is canceled) the
	 * {@link BillingReceiver} receives an intent with the action {@link BillingReceiver#ACTION_NOTIFY}. Returns false
	 * if there was an error trying to connect to the Google Play app.
	 * 
	 * @param purchasable the product to purchase
	 * @param developerPayload a payload that is associated with a given purchase, if null, no payload is sent
	 * @return false if there was an error connecting to the Google Play app
	 */
	public boolean requestPurchase(Purchasable purchasable, String developerPayload);
	
	public void bind(Context context);
	
	/**
	 * Unbinds from the MarketBillingService. Call this when the application terminates to avoid leaking a
	 * ServiceConnection.
	 */
	public void unbind();
	
	/**
	 * Gets the purchase information. This message includes a list of notification IDs sent to us by the Google Play
	 * app, which we include in our request. The server responds with the purchase information, encoded as a JSON
	 * string, and sends that to the {@link BillingReceiver} in an intent with the action
	 * {@link BillingReceiver#ACTION_PURCHASE_STATE_CHANGED}. Returns false if there was an error trying to connect to
	 * the MarketBillingService.
	 * 
	 * @param notifyIds a list of opaque identifiers associated with purchase state changes
	 * @return false if there was an error connecting to the Google Play app
	 */
	public boolean getPurchaseInformation(String[] notifyIds);
	
	/**
	 * Verifies that the data was signed with the given signature, and calls
	 * {@link BillingListener#onPurchased(PurchaseOrder)}, {@link BillingListener#onRefunded(PurchaseOrder)} or
	 * {@link BillingListener#onCanceled(PurchaseOrder)} for each verified purchase.
	 * 
	 * @param signedData the signed JSON string (signed, not encrypted)
	 * @param signature the signature for the data, signed with the private key
	 */
	public void purchaseStateChanged(String signedData, String signature);
	
	/**
	 * This is called when we receive a response code from the Google Play app for a request that we made. This is used
	 * for reporting various errors and for acknowledging that an order was sent to the server. This is NOT used for any
	 * purchase state changes. All purchase state changes are received in the {@link BillingReceiver} and passed to this
	 * service, where they are handled in {@link #purchaseStateChanged(String, String)}.
	 * 
	 * @param requestId a number that identifies a request, assigned at the time the request was made to the Google Play
	 *            app
	 * @param responseCode a response code from the Google Play app to indicate the state of the request
	 */
	public void checkResponseCode(long requestId, BillingResponseCode responseCode);
	
	/**
	 * Registers a listener to listen events
	 * 
	 * @param billingListener the listener to register
	 */
	public void register(BillingListener billingListener);
	
	/**
	 * Unregisters a previously registered listener.
	 * 
	 * @param billingListener the previously registered listener.
	 */
	public void unregister(BillingListener billingListener);
	
	/**
	 * Registers an observer to listen events
	 * 
	 * @param observer the observer to register
	 */
	public void register(PurchaseObserver observer);
	
	/**
	 * Unregisters a previously registered observer.
	 * 
	 * @param observer the previously registered observer.
	 */
	public void unregister(PurchaseObserver observer);
	
}

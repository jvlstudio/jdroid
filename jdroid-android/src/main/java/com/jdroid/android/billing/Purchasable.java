package com.jdroid.android.billing;

/**
 * 
 * @author Maxi Rosson
 */
public interface Purchasable {
	
	/**
	 * @return an identifier for the item being offered for purchase
	 */
	public String getProductId();
	
	public PurchaseState getPurchaseState();
	
	/**
	 * @return Whether the product is available to use because it is free or it is already purchased
	 */
	public Boolean isAvailable();
	
}

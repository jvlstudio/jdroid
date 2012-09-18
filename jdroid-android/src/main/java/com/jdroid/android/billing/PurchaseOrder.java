package com.jdroid.android.billing;

import java.io.Serializable;

/**
 * A class to hold the verified purchase order information.
 * 
 * @author Maxi Rosson
 */
public class PurchaseOrder implements Serializable {
	
	private PurchaseState purchaseState;
	private String notificationId;
	private String productId;
	private String orderId;
	private long purchaseTime;
	private String developerPayload;
	
	public PurchaseOrder(PurchaseState purchaseState, String notificationId, String productId, String orderId,
			long purchaseTime, String developerPayload) {
		this.purchaseState = purchaseState;
		this.notificationId = notificationId;
		this.productId = productId;
		this.orderId = orderId;
		this.purchaseTime = purchaseTime;
		this.developerPayload = developerPayload;
	}
	
	/**
	 * @return the state of the purchase request (PURCHASED, CANCELED, or REFUNDED)
	 */
	public PurchaseState getPurchaseState() {
		return purchaseState;
	}
	
	public String getNotificationId() {
		return notificationId;
	}
	
	/**
	 * @return a string identifying a product for sale (the "SKU")
	 */
	public String getProductId() {
		return productId;
	}
	
	/**
	 * @return a string identifying the order
	 */
	public String getOrderId() {
		return orderId;
	}
	
	/**
	 * @return the time the product was purchased, in milliseconds since the epoch (Jan 1, 1970)
	 */
	public long getPurchaseTime() {
		return purchaseTime;
	}
	
	/**
	 * @return the developer provided "payload" associated with the order
	 */
	public String getDeveloperPayload() {
		return developerPayload;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VerifiedPurchase [purchaseState=" + purchaseState + ", notificationId=" + notificationId
				+ ", productId=" + productId + ", orderId=" + orderId + ", purchaseTime=" + purchaseTime
				+ ", developerPayload=" + developerPayload + "]";
	}
}
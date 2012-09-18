package com.jdroid.android.billing;

/**
 * The possible states of an in-app purchase
 * 
 * @author Maxi Rosson
 */
public enum PurchaseState {
	
	/** The product is not available to purchase because it is free. */
	FREE(true),
	
	/** The product wasn't purchased. */
	NOT_PURCHASED(false),
	
	/** The product is not available to purchase */
	NOT_AVAILABLE(false),
	
	/** The product was requested to purchase */
	PURCHASE_REQUEST(false),
	
	/** The product was purchased by the user but the request is being processed by Google. */
	PENDIND_PURCHASE(false),
	
	/** User was charged for the order but the product was not registered as purchased on the application server. */
	PURCHASED(false, 0),
	
	/** The charge failed on the server. */
	CANCELED(false, 1),
	
	/** User received a refund for the order. */
	REFUNDED(false, 2),
	
	/** The product was registered as purchased on the application server. */
	REGISTERED(true);
	
	private Integer code;
	private Boolean isAvailable;
	
	private PurchaseState(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	private PurchaseState(Boolean isAvailable, int code) {
		this.isAvailable = isAvailable;
		this.code = code;
	}
	
	/**
	 * Converts from an ordinal value to the PurchaseState
	 * 
	 * @param code
	 * @return The {@link PurchaseState}
	 */
	public static PurchaseState valueOf(Integer code) {
		PurchaseState purchaseState = null;
		for (PurchaseState each : values()) {
			if (code.equals(each.code)) {
				return each;
			}
		}
		return purchaseState;
	}
	
	/**
	 * @return Whether the product is available to use because it is free or it is already purchased
	 */
	public Boolean isAvailable() {
		return isAvailable;
	}
}

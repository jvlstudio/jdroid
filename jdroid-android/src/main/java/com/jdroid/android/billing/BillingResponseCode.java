package com.jdroid.android.billing;

import android.content.Intent;
import android.os.Bundle;
import com.jdroid.android.billing.BillingManagerImpl.BillingRequest;

/**
 * The response codes for a request, defined by the Google Play app.
 * 
 * @author Maxi Rosson
 */
public enum BillingResponseCode {
	
	// Indicates that the request was sent to the server successfully. When this code is returned in response to a
	// CHECK_BILLING_SUPPORTED request, indicates that billing is supported.
	RESULT_OK(0),
	
	// Indicates that the user pressed the back button on the checkout page instead of buying the item.
	RESULT_USER_CANCELED(1),
	
	// Indicates that the network connection is down.
	RESULT_SERVICE_UNAVAILABLE(2),
	
	// Indicates that in-app billing is not available because the API_VERSION that you specified is not recognized by
	// the Google Play app or the user is ineligible for in-app billing (for example, the user resides in a
	// country that prohibits in-app purchases).
	RESULT_BILLING_UNAVAILABLE(3),
	
	// Indicates that the Google Play app cannot find the requested item in the application's product list. This can
	// happen
	// if the product ID is misspelled in your REQUEST_PURCHASE request or if an item is unpublished in the
	// application's product list.
	RESULT_ITEM_UNAVAILABLE(4),
	
	// Indicates that an application is trying to make an in-app billing request but the application has not declared
	// the com.android.vending.BILLING permission in its manifest. Can also indicate that an application is not properly
	// signed, or that you sent a malformed request, such as a request with missing Bundle keys or a request that uses
	// an unrecognized request type.
	RESULT_DEVELOPER_ERROR(5),
	
	// Indicates an unexpected server error. For example, this error is triggered if you try to purchase an item from
	// yourself, which is not allowed by Google Checkout.
	RESULT_ERROR(6);
	
	private int code;
	
	private BillingResponseCode(int code) {
		this.code = code;
	}
	
	public static BillingResponseCode valueOf(Bundle bundle) {
		int intResponseCode = bundle.getInt(BillingRequest.BILLING_RESPONSE_RESPONSE_CODE);
		return BillingResponseCode.valueOf(intResponseCode);
	}
	
	public static BillingResponseCode valueOf(Intent intent) {
		int intResponseCode = intent.getIntExtra(BillingService.INAPP_RESPONSE_CODE,
			BillingResponseCode.RESULT_ERROR.code);
		return BillingResponseCode.valueOf(intResponseCode);
	}
	
	/**
	 * Converts from an ordinal value to the ResponseCode
	 * 
	 * @param code
	 * @return The {@link BillingResponseCode}
	 */
	private static BillingResponseCode valueOf(int code) {
		BillingResponseCode responseCode = BillingResponseCode.RESULT_ERROR;
		for (BillingResponseCode each : values()) {
			if (each.code == code) {
				return each;
			}
		}
		return responseCode;
	}
	
	public int getCode() {
		return code;
	}
}

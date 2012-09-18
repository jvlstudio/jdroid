package com.jdroid.android.billing;

import java.util.List;
import com.jdroid.java.collections.Lists;

/**
 * 
 * @author Maxi Rosson
 */
public class MockBillingManager extends BillingManagerImpl {
	
	private TestProduct testProduct = TestProduct.PURCHASED;
	private Purchasable purchasable;
	
	public void setTestProduct(TestProduct testProduct) {
		this.testProduct = testProduct;
	}
	
	/**
	 * @see com.jdroid.android.billing.BillingManagerImpl#requestPurchase(com.jdroid.android.billing.Purchasable,
	 *      java.lang.String)
	 */
	@Override
	public boolean requestPurchase(Purchasable purchasable, String developerPayload) {
		this.purchasable = purchasable;
		return super.requestPurchase(testProduct, developerPayload);
	}
	
	/**
	 * @see com.jdroid.android.billing.BillingManagerImpl#purchaseStateChanged(java.lang.String, java.lang.String)
	 */
	@Override
	public void purchaseStateChanged(String signedData, String signature) {
		List<PurchaseOrder> orders = Security.verifyPurchase(signedData, signature);
		List<PurchaseOrder> mockedOrders = Lists.newArrayList();
		if ((orders != null) && !orders.isEmpty()) {
			for (PurchaseOrder verifiedPurchase : orders) {
				if (purchasable != null) {
					mockedOrders.add(new PurchaseOrder(verifiedPurchase.getPurchaseState(),
							verifiedPurchase.getNotificationId(), purchasable.getProductId(),
							verifiedPurchase.getOrderId(), verifiedPurchase.getPurchaseTime(),
							verifiedPurchase.getDeveloperPayload()));
				}
			}
			BillingResponseHandler.notifyPurchaseStateChanged(mockedOrders);
		}
	}
}

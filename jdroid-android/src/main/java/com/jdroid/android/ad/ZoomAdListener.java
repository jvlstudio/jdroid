package com.jdroid.android.ad;

import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.AdView;

/**
 * 
 * @author Maxi Rosson
 */
public class ZoomAdListener implements AdListener {
	
	private AdView adView;
	
	/**
	 * @param adView
	 */
	public ZoomAdListener(AdView adView) {
		this.adView = adView;
	}
	
	/**
	 * @see com.google.ads.AdListener#onDismissScreen(com.google.ads.Ad)
	 */
	@Override
	public void onDismissScreen(Ad ad) {
		// Do Nothing
	}
	
	/**
	 * @see com.google.ads.AdListener#onLeaveApplication(com.google.ads.Ad)
	 */
	@Override
	public void onLeaveApplication(Ad ad) {
		// Do Nothing
	}
	
	/**
	 * @see com.google.ads.AdListener#onPresentScreen(com.google.ads.Ad)
	 */
	@Override
	public void onPresentScreen(Ad ad) {
		// Do Nothing
	}
	
	/**
	 * @see com.google.ads.AdListener#onFailedToReceiveAd(com.google.ads.Ad, com.google.ads.AdRequest.ErrorCode)
	 */
	@Override
	public void onFailedToReceiveAd(Ad ad, ErrorCode errorCode) {
		// Do Nothing
	}
	
	/**
	 * @see com.google.ads.AdListener#onReceiveAd(com.google.ads.Ad)
	 */
	@Override
	public void onReceiveAd(Ad ad) {
		ScaleAnimation zoomIn = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, .5f,
				Animation.RELATIVE_TO_SELF, .5f);
		zoomIn.setDuration(500);
		adView.startAnimation(zoomIn);
	}
}

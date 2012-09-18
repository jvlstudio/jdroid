package com.jdroid.android.fragment;

import roboguice.RoboGuice;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.jdroid.android.R;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.google.ads.mediation.admob.AdMobAdapterExtras;
import com.jdroid.android.AbstractApplication;
import com.jdroid.android.ad.ZoomAdListener;
import com.jdroid.android.context.DefaultApplicationContext;
import com.jdroid.android.usecase.DefaultAbstractUseCase;
import com.jdroid.android.usecase.listener.DefaultUseCaseListener;
import com.jdroid.java.utils.ExecutorUtils;

/**
 * 
 * @author Maxi Rosson
 */
public class BaseFragment {
	
	private final static String TAG = BaseFragment.class.getSimpleName();
	
	private Fragment fragment;
	
	public BaseFragment(Fragment fragment) {
		this.fragment = fragment;
	}
	
	public FragmentIf getFragmentIf() {
		return (FragmentIf)fragment;
	}
	
	public void onCreate(Bundle savedInstanceState) {
		Log.v(TAG, "Executing onCreate on " + fragment);
		RoboGuice.getInjector(fragment.getActivity()).injectMembersWithoutViews(fragment);
	}
	
	public void onViewCreated(View view, Bundle savedInstanceState) {
		Log.v(TAG, "Executing onViewCreated on " + fragment);
		RoboGuice.getInjector(fragment.getActivity()).injectViewMembers(fragment);
		
		// Ads Management
		DefaultApplicationContext applicationContext = AbstractApplication.get().getAndroidApplicationContext();
		ViewGroup adViewContainer = (ViewGroup)(fragment.getView().findViewById(R.id.adViewContainer));
		if (adViewContainer != null) {
			AdSize adSize = getFragmentIf().getAdSize();
			if ((adSize == null) || !applicationContext.areAdsEnabled()) {
				adViewContainer.setVisibility(View.GONE);
			} else {
				
				AdView adView = new AdView(fragment.getActivity(), adSize, applicationContext.getAdUnitId());
				AdRequest adRequest = new AdRequest();
				AdMobAdapterExtras extras = new AdMobAdapterExtras();
				
				// Background color
				extras.addExtra("color_bg", "AAAAFF");
				// Gradient background color at top
				extras.addExtra("color_bg_top", "FFFFFF");
				// Border color
				extras.addExtra("color_border", "FFFFFF");
				// Link text color
				extras.addExtra("color_link", "000080");
				// Text color
				extras.addExtra("color_text", "808080");
				// URL color
				extras.addExtra("color_url", "008000");
				adRequest.setNetworkExtras(extras);
				
				if (!applicationContext.isProductionEnvironment()) {
					adRequest.setTestDevices(applicationContext.getTestDevicesIds());
					adRequest.addTestDevice(AdRequest.TEST_EMULATOR);
				}
				adView.setAdListener(new ZoomAdListener(adView));
				adView.loadAd(adRequest);
				adViewContainer.addView(adView);
			}
		}
	}
	
	public void onActivityCreated(Bundle savedInstanceState) {
		Log.v(TAG, "Executing onActivityCreated on " + fragment);
	}
	
	public void onStart() {
		Log.v(TAG, "Executing onStart on " + fragment);
	}
	
	public void onResume() {
		Log.v(TAG, "Executing onResume on " + fragment);
	}
	
	public void onPause() {
		Log.v(TAG, "Executing onPause on " + fragment);
	}
	
	public void onStop() {
		Log.v(TAG, "Executing onStop on " + fragment);
	}
	
	public void onDestroyView() {
		Log.v(TAG, "Executing onDestroyView on " + fragment);
	}
	
	public void onDestroy() {
		Log.v(TAG, "Executing onDestroy on " + fragment);
	}
	
	public void onResumeUseCase(DefaultAbstractUseCase useCase, DefaultUseCaseListener listener) {
		onResumeUseCase(useCase, listener, false);
	}
	
	public void onResumeUseCase(final DefaultAbstractUseCase useCase, final DefaultUseCaseListener listener,
			final Boolean initUseCase) {
		ExecutorUtils.execute(new Runnable() {
			
			@Override
			public void run() {
				useCase.addListener(listener);
				if (!useCase.isNotified()) {
					if (useCase.isInProgress()) {
						listener.onStartUseCase();
					} else if (useCase.isFinishSuccessful()) {
						listener.onFinishUseCase();
						useCase.markAsNotified();
					} else if (useCase.isFinishFailed()) {
						try {
							listener.onFinishFailedUseCase(useCase.getRuntimeException());
						} finally {
							useCase.markAsNotified();
						}
					} else if (useCase.isNotInvoked() && initUseCase) {
						useCase.run();
					}
				}
			}
		});
	}
	
	public void onPauseUseCase(final DefaultAbstractUseCase userCase, final DefaultUseCaseListener listener) {
		if (userCase != null) {
			userCase.removeListener(listener);
		}
	}
}

package com.jdroid.android.debug;

import android.os.Bundle;
import android.view.View;
import com.jdroid.android.R;
import com.jdroid.android.activity.AbstractPreferenceActivity;

/**
 * 
 * @author Maxi Rosson
 */
public class PreHoneycombDebugSettingsActivity extends AbstractPreferenceActivity {
	
	/**
	 * @see android.preference.PreferenceActivity#onCreate(android.os.Bundle)
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.debug_preferences);
		
		View debugInfoView = DebugSettingsFragment.inflateDebugInfoView(this);
		
		getListView().addFooterView(debugInfoView);
	}
	
	/**
	 * @see com.jdroid.android.activity.AbstractPreferenceActivity#requiresAuthentication()
	 */
	@Override
	public Boolean requiresAuthentication() {
		return false;
	}
}

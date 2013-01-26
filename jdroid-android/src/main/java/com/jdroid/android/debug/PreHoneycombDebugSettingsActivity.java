package com.jdroid.android.debug;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.jdroid.android.R;
import com.jdroid.android.activity.AbstractPreferenceActivity;
import com.jdroid.android.utils.AndroidUtils;

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
		
		View header = inflate(R.layout.device_info_header);
		((TextView)header.findViewById(R.id.screenSize)).setText("Screen Size: " + AndroidUtils.getScreenSize());
		((TextView)header.findViewById(R.id.screenDensity)).setText("Screen Density: "
				+ AndroidUtils.getScreenDensity());
		
		getListView().addFooterView(header);
	}
	
	/**
	 * @see com.jdroid.android.activity.AbstractPreferenceActivity#requiresAuthentication()
	 */
	@Override
	public Boolean requiresAuthentication() {
		return false;
	}
}

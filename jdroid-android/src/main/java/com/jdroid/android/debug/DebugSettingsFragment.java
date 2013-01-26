package com.jdroid.android.debug;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.jdroid.android.R;
import com.jdroid.android.fragment.AbstractPreferenceFragment;
import com.jdroid.android.utils.AndroidUtils;

/**
 * 
 * @author Maxi Rosson
 */
public class DebugSettingsFragment extends AbstractPreferenceFragment {
	
	/**
	 * @see android.preference.PreferenceActivity#onCreate(android.os.Bundle)
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		addPreferencesFromResource(R.xml.debug_preferences);
	}
	
	/**
	 * @see android.app.Fragment#onViewCreated(android.view.View, android.os.Bundle)
	 */
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		View header = inflate(R.layout.device_info_header);
		((TextView)header.findViewById(R.id.screenSize)).setText("Screen Size: " + AndroidUtils.getScreenSize());
		((TextView)header.findViewById(R.id.screenDensity)).setText("Screen Density: "
				+ AndroidUtils.getScreenDensity());
		
		ListView listView = ((ListView)findView(android.R.id.list));
		listView.addFooterView(header);
	}
}

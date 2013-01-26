package com.jdroid.android.debug;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.jdroid.android.AbstractApplication;
import com.jdroid.android.R;
import com.jdroid.android.context.DefaultApplicationContext;
import com.jdroid.android.fragment.AbstractPreferenceFragment;
import com.jdroid.android.utils.AndroidUtils;
import com.jdroid.java.context.GitContext;

/**
 * 
 * @author Maxi Rosson
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class DebugSettingsFragment extends AbstractPreferenceFragment {
	
	/**
	 * @see android.preference.PreferenceActivity#onCreate(android.os.Bundle)
	 */
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
		
		View debugInfoView = inflateDebugInfoView(getActivity());
		
		ListView listView = ((ListView)findView(android.R.id.list));
		listView.addFooterView(debugInfoView);
	}
	
	public static View inflateDebugInfoView(Activity activity) {
		View debugInfoView = LayoutInflater.from(activity).inflate(R.layout.debug_info_view, null);
		
		TextView packageName = (TextView)debugInfoView.findViewById(R.id.packageName);
		packageName.setText(activity.getString(R.string.packageName, AndroidUtils.getPackageName()));
		
		DefaultApplicationContext applicationContext = AbstractApplication.get().getAndroidApplicationContext();
		TextView environmentName = (TextView)debugInfoView.findViewById(R.id.environmentName);
		environmentName.setText(activity.getString(R.string.environmentName, applicationContext.getEnvironmentName()));
		
		TextView analyticsEnabled = (TextView)debugInfoView.findViewById(R.id.analyticsEnabled);
		analyticsEnabled.setText(activity.getString(R.string.analyticsEnabled, applicationContext.isAnalyticsEnabled()));
		
		TextView screenSize = (TextView)debugInfoView.findViewById(R.id.screenSize);
		screenSize.setText(activity.getString(R.string.screenSize, AndroidUtils.getScreenSize()));
		
		TextView screenDensity = (TextView)debugInfoView.findViewById(R.id.screenDensity);
		screenDensity.setText(activity.getString(R.string.screenDensity, AndroidUtils.getScreenDensity()));
		
		TextView commitId = (TextView)debugInfoView.findViewById(R.id.commitId);
		if (GitContext.get().getCommitId() != null) {
			commitId.setText(activity.getString(R.string.commitId, GitContext.get().getCommitId()));
		} else {
			commitId.setVisibility(View.GONE);
		}
		
		TextView commitTime = (TextView)debugInfoView.findViewById(R.id.commitTime);
		if (GitContext.get().getCommitTime() != null) {
			commitTime.setText(activity.getString(R.string.commitTime, GitContext.get().getCommitTime()));
		} else {
			commitTime.setVisibility(View.GONE);
		}
		
		TextView buildTime = (TextView)debugInfoView.findViewById(R.id.buildTime);
		if (GitContext.get().getBuildTime() != null) {
			buildTime.setText(activity.getString(R.string.buildTime, GitContext.get().getBuildTime()));
		} else {
			buildTime.setVisibility(View.GONE);
		}
		
		return debugInfoView;
	}
}

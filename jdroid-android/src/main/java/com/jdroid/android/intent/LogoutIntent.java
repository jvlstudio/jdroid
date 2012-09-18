package com.jdroid.android.intent;

import android.content.Intent;
import android.content.IntentFilter;
import com.jdroid.android.AbstractApplication;

public class LogoutIntent {
	
	private static final String LOGOUT_ACTION = "com.android.LOGOUT_ACTION";
	
	public static void execute() {
		Intent broadcastIntent = new Intent();
		broadcastIntent.setAction(LOGOUT_ACTION);
		AbstractApplication.get().sendBroadcast(broadcastIntent);
	}
	
	public static IntentFilter newIntentFilter() {
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(LOGOUT_ACTION);
		return intentFilter;
	}
	
}

package com.jdroid.android.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;
import com.jdroid.android.AbstractApplication;

/**
 * 
 * @author Maxi Rosson
 */
public class IntentRetryUtils {
	
	private static final String TAG = IntentRetryUtils.class.getSimpleName();
	
	// Used internally to count retries.
	private static final String EXTRA_CURRENT_RETRY_COUNT = "currentRetryCount";
	private static final String EXTRA_CURRENT_BACKOFF = "currentBackOff";
	
	// The default backoff in milliseconds
	private static final long DEFAULT_BACKOFF = 5000;
	
	// The maximum number of tries to send a report.
	private static final int DEFAULT_MAXIMUM_RETRY_COUNT = 10;
	
	public static Boolean retry(Intent intent) {
		return retry(intent, DEFAULT_MAXIMUM_RETRY_COUNT);
	}
	
	public static Boolean retry(Intent intent, Integer maximumRetryCount) {
		return retry(intent, maximumRetryCount, DEFAULT_BACKOFF);
	}
	
	public static Boolean retry(Intent intent, Integer maximumRetryCount, Long startBackoff) {
		
		int count = intent.getIntExtra(EXTRA_CURRENT_RETRY_COUNT, 0);
		long backoff = intent.getLongExtra(EXTRA_CURRENT_BACKOFF, startBackoff);
		
		intent.putExtra(EXTRA_CURRENT_RETRY_COUNT, count + 1);
		intent.putExtra(EXTRA_CURRENT_BACKOFF, backoff * 2);
		
		PendingIntent operation = PendingIntent.getService(AbstractApplication.get(), 0, intent,
			PendingIntent.FLAG_CANCEL_CURRENT);
		if (count >= maximumRetryCount) {
			// Discard retry
			Log.w(TAG, "Operation reached the maximum retry count and will be discarded.");
			return false;
		}
		// Retry using an exponential backoff
		AlarmManagerUtils.scheduleAlarm(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + backoff,
			operation);
		return true;
	}
	
	public static void schedule(Intent intent, Long frequency) {
		
		PendingIntent operation = PendingIntent.getService(AbstractApplication.get(), 0, intent,
			PendingIntent.FLAG_CANCEL_CURRENT);
		
		AlarmManagerUtils.scheduleAlarm(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + frequency,
			operation);
	}
	
}

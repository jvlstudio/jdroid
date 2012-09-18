package com.jdroid.android.service;

import roboguice.service.RoboIntentService;
import android.content.Context;
import android.content.Intent;
import com.jdroid.android.utils.WakeLockManager;

/**
 * 
 * @author Maxi Rosson
 */
public abstract class WorkerService extends RoboIntentService {
	
	private static final String TAG = WorkerService.class.getSimpleName();
	
	public WorkerService() {
		super(TAG);
	}
	
	/**
	 * @see android.app.IntentService#onHandleIntent(android.content.Intent)
	 */
	@Override
	protected final void onHandleIntent(Intent intent) {
		try {
			doExecute(intent);
		} finally {
			WakeLockManager.releasePartialWakeLock();
		}
	}
	
	protected abstract void doExecute(Intent intent);
	
	protected static void runIntentInService(Context context, Intent intent, Class<? extends WorkerService> serviceClass) {
		WakeLockManager.acquirePartialWakeLock(context);
		intent.setClass(context, serviceClass);
		context.startService(intent);
	}
	
}

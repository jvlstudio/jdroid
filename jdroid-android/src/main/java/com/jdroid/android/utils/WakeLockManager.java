package com.jdroid.android.utils;

import android.content.Context;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;

/**
 * Manager that control the acquire and release of the different kinds of {@link WakeLock}s
 * 
 * @author Maxi Rosson
 */
public class WakeLockManager {
	
	private final static String TAG = WakeLockManager.class.getSimpleName();
	
	private static PowerManager.WakeLock screenDimWakeLock;
	private static PowerManager.WakeLock partialWakeLock;
	
	/**
	 * Makes sure the device is on at the {@link PowerManager#SCREEN_DIM_WAKE_LOCK} level when you created the wake
	 * lock.
	 * 
	 * @param context The {@link Context}
	 */
	public static synchronized void acquireScreenDimWakeLock(Context context) {
		screenDimWakeLock = aquire(context, screenDimWakeLock, PowerManager.SCREEN_DIM_WAKE_LOCK);
	}
	
	/**
	 * Release your claim to the {@link PowerManager#SCREEN_DIM_WAKE_LOCK} being on.
	 */
	public static synchronized void releaseScreenDimWakeLock() {
		release(screenDimWakeLock);
		screenDimWakeLock = null;
	}
	
	/**
	 * Makes sure the device is on at the {@link PowerManager#PARTIAL_WAKE_LOCK} level when you created the wake lock.
	 * 
	 * @param context The {@link Context}
	 */
	public static synchronized void acquirePartialWakeLock(Context context) {
		partialWakeLock = aquire(context, partialWakeLock, PowerManager.PARTIAL_WAKE_LOCK);
	}
	
	/**
	 * Release your claim to the {@link PowerManager#PARTIAL_WAKE_LOCK} being on.
	 */
	public static synchronized void releasePartialWakeLock() {
		release(partialWakeLock);
		partialWakeLock = null;
	}
	
	private static void release(PowerManager.WakeLock wakeLock) {
		if (wakeLock != null) {
			Log.v(TAG, "Releasing wakelock");
			wakeLock.release();
		}
	}
	
	private static PowerManager.WakeLock aquire(Context context, PowerManager.WakeLock wakeLock, int flags) {
		if (wakeLock == null) {
			PowerManager pm = (PowerManager)context.getSystemService(Context.POWER_SERVICE);
			wakeLock = pm.newWakeLock(flags, TAG);
		}
		Log.v(TAG, "Acquiring wakelock");
		wakeLock.acquire();
		return wakeLock;
	}
}

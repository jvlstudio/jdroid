package com.jdroid.android.twitter;

import twitter4j.TwitterException;
import android.util.Log;

/**
 * 
 * @author Maxi Rosson
 */
public enum TwitterErrorType {
	
	OAUTH_ERROR(401);
	
	private static final String TAG = TwitterErrorType.class.getSimpleName();
	
	private Integer errorCode;
	
	private TwitterErrorType(Integer errorCode) {
		this.errorCode = errorCode;
	}
	
	public static TwitterErrorType find(TwitterException twitterException) {
		for (TwitterErrorType each : values()) {
			if (each.errorCode.equals(twitterException.getStatusCode())) {
				return each;
			}
		}
		Log.w(TAG, "The Twitter status code [" + twitterException.getStatusCode() + "] is unknown");
		return null;
	}
	
}

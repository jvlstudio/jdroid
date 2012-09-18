package com.jdroid.android.utils;

import com.jdroid.android.AbstractApplication;
import com.jdroid.java.exception.ErrorCode;

public final class LocalizationUtils {
	
	/**
	 * Returns a formatted string, using the localized resource as format and the supplied arguments
	 * 
	 * @param resId The resource id to obtain the format
	 * @param args arguments to replace format specifiers
	 * @return The localized and formated string
	 */
	public static String getString(int resId, Object... args) {
		return AbstractApplication.get().getString(resId, args);
	}
	
	public static String getMessageFor(ErrorCode errorCode) {
		return LocalizationUtils.getString(errorCode.getResourceId());
	}
	
	public static String getMessageFor(ErrorCode errorCode, Object... args) {
		return LocalizationUtils.getString(errorCode.getResourceId(), args);
	}
}

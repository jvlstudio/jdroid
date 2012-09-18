package com.jdroid.android.utils;

import java.text.DateFormat;
import java.util.Date;
import com.jdroid.android.AbstractApplication;
import com.jdroid.java.utils.DateUtils;

/**
 * Date Utils that returns formatted times & dates according to the current locale and user preferences
 * 
 * @author Maxi Rosson
 */
public class AndroidDateUtils {
	
	/**
	 * @return The formatted time
	 */
	public static String getFormattedTime() {
		return AndroidDateUtils.getFormattedTime(DateUtils.now());
	}
	
	/**
	 * @param date The {@link Date} to format
	 * @return The formatted time
	 */
	public static String getFormattedTime(Date date) {
		DateFormat dateFormat = android.text.format.DateFormat.getTimeFormat(AbstractApplication.get());
		return DateUtils.format(DateUtils.now(), dateFormat);
	}
	
}

package com.jdroid.android.utils;

import com.jdroid.android.AbstractApplication;

/**
 * 
 * @author Maxi Rosson
 */
public class MeasureUtils {
	
	public static int pxToDp(int px) {
		final float scale = AbstractApplication.get().getResources().getDisplayMetrics().density;
		return (int)((px * scale) + 0.5f);
	}
	
}

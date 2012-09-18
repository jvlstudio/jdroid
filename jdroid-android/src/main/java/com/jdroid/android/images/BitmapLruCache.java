package com.jdroid.android.images;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * 
 * @author Maxi Rosson
 */
public class BitmapLruCache extends LruCache<String, Bitmap> {
	
	public BitmapLruCache(int maxSizeBytes) {
		super(maxSizeBytes);
	}
	
	/**
	 * @see android.support.v4.util.LruCache#sizeOf(java.lang.Object, java.lang.Object)
	 */
	@Override
	protected int sizeOf(String key, Bitmap value) {
		return value.getRowBytes() * value.getHeight();
	}
}

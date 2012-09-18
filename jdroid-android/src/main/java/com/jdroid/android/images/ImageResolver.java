package com.jdroid.android.images;

import android.graphics.Bitmap;
import android.net.Uri;

/**
 * 
 * @author Maxi Rosson
 */
public interface ImageResolver {
	
	/**
	 * @param uri The {@link Uri} to verify
	 * @return Whether this {@link ImageResolver} can resolve the {@link Uri} or not
	 */
	public Boolean canResolve(Uri uri);
	
	public Bitmap resolve(Uri uri, Integer maxWidth, Integer maxHeight);
}

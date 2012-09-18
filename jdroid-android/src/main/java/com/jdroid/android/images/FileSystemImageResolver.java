package com.jdroid.android.images;

import android.graphics.Bitmap;
import android.net.Uri;
import com.jdroid.android.utils.BitmapUtils;

/**
 * 
 * @author Maxi Rosson
 */
public class FileSystemImageResolver implements ImageResolver {
	
	private static final FileSystemImageResolver INSTANCE = new FileSystemImageResolver();
	
	public static FileSystemImageResolver get() {
		return INSTANCE;
	}
	
	/**
	 * @see com.jdroid.android.images.ImageResolver#canResolve(android.net.Uri)
	 */
	@Override
	public Boolean canResolve(Uri uri) {
		return (uri.getScheme() != null) && uri.getScheme().equals("file");
	}
	
	/**
	 * @see com.jdroid.android.images.ImageResolver#resolve(android.net.Uri, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Bitmap resolve(Uri uri, Integer maxWidth, Integer maxHeight) {
		return BitmapUtils.toBitmap(uri, maxWidth, maxHeight);
	}
	
}

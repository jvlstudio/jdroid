package com.jdroid.android.contacts;

import java.io.InputStream;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.util.Log;
import com.jdroid.android.AbstractApplication;
import com.jdroid.android.images.ImageResolver;
import com.jdroid.android.utils.BitmapUtils;

/**
 * 
 * @author Maxi Rosson
 */
public class ContactImageResolver implements ImageResolver {
	
	private static final String TAG = ContactImageResolver.class.getSimpleName();
	
	private static final ContactImageResolver INSTANCE = new ContactImageResolver();
	
	public static ContactImageResolver get() {
		return INSTANCE;
	}
	
	/**
	 * @see com.jdroid.android.images.ImageResolver#canResolve(android.net.Uri)
	 */
	@Override
	public Boolean canResolve(Uri uri) {
		return uri.toString().startsWith(Contacts.CONTENT_URI.toString());
	}
	
	/**
	 * @see com.jdroid.android.images.ImageResolver#resolve(android.net.Uri, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Bitmap resolve(Uri uri, Integer maxWidth, Integer maxHeight) {
		InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(
			AbstractApplication.get().getContentResolver(), uri);
		Bitmap bitmap = null;
		if (input != null) {
			bitmap = BitmapUtils.toBitmap(input, maxWidth, maxHeight);
			Log.d(TAG, "Image [" + uri.toString() + "] loaded.");
		} else {
			Log.d(TAG, "Image [" + uri.toString() + "] not found.");
		}
		return bitmap;
	}
}

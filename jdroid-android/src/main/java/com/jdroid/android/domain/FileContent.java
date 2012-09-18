package com.jdroid.android.domain;

import java.io.Serializable;
import android.net.Uri;

/**
 * 
 * @author Maxi Rosson
 */
public abstract class FileContent implements Serializable {
	
	public abstract Uri getUri();
	
	public String getUriAsString() {
		Uri uri = getUri();
		return uri != null ? uri.toString() : null;
	}
}
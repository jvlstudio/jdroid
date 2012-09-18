package com.jdroid.android.domain;

import android.net.Uri;

/**
 * 
 * @author Maxi Rosson
 */
public class DefaultFileContent extends FileContent {
	
	private Uri uri;
	
	public DefaultFileContent(Uri uri) {
		this.uri = uri;
	}
	
	/**
	 * @see com.jdroid.android.domain.FileContent#getUri()
	 */
	@Override
	public Uri getUri() {
		return uri;
	}
}

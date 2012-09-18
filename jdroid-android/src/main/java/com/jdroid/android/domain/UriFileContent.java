package com.jdroid.android.domain;

import android.net.Uri;
import com.jdroid.java.utils.StringUtils;

/**
 * 
 * @author Maxi Rosson
 */
public class UriFileContent extends FileContent {
	
	private String uri;
	
	public UriFileContent(Uri uri) {
		this.uri = uri.toString();
	}
	
	public UriFileContent(String uri) {
		this.uri = uri;
	}
	
	/**
	 * @see com.jdroid.android.domain.FileContent#getUri()
	 */
	@Override
	public Uri getUri() {
		return StringUtils.isNotEmpty(uri) ? Uri.parse(uri) : null;
	}
}

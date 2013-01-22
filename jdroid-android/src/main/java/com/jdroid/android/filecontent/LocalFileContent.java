package com.jdroid.android.filecontent;

import java.io.File;
import android.net.Uri;
import com.jdroid.android.domain.FileContent;
import com.jdroid.android.utils.AndroidUtils;

public class LocalFileContent extends FileContent {
	
	private static final String ANDROID_RESOURCE = "android.resource://";
	
	private Integer resourceId;
	
	/**
	 * @param resourceId
	 */
	public LocalFileContent(Integer resourceId) {
		this.resourceId = resourceId;
	}
	
	/**
	 * @see com.jdroid.android.domain.FileContent#getUri()
	 */
	@Override
	public Uri getUri() {
		return Uri.parse(ANDROID_RESOURCE + AndroidUtils.getPackageName() + File.separator + resourceId);
	}
	
}

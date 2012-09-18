package com.jdroid.android.images;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * 
 * @author Maxi Rosson
 */
public interface ImageHolder {
	
	public void setImageBitmap(Bitmap bitmap);
	
	public void showStubImage();
	
	public Integer getMaxWidth();
	
	public Integer getMaxHeight();
	
	public Object getTag();
	
	public Context getContext();
}

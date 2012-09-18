package com.jdroid.android.coverflow;

import java.util.List;
import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import com.jdroid.android.adapter.BaseArrayAdapter;
import com.jdroid.android.domain.FileContent;
import com.jdroid.android.images.CustomImageView;
import com.jdroid.android.images.ReflectedRemoteImageResolver;

/**
 * This class is an adapter that provides base, abstract class for images adapter.
 * 
 * @param <T>
 * 
 */
public abstract class CoverFlowImageAdapter<T> extends BaseArrayAdapter<T> {
	
	private float width;
	private float height;
	
	public CoverFlowImageAdapter(Context context, List<T> objects, float width, float height) {
		super(context, objects);
		this.width = width;
		this.height = height * (1 + ReflectedRemoteImageResolver.get().getImageReflectionRatio());
	}
	
	/**
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	/**
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public CustomImageView getView(int position, View convertView, ViewGroup parent) {
		CustomImageView imageView;
		if (convertView == null) {
			imageView = new CustomImageView(parent.getContext());
			imageView.setLayoutParams(new CoverFlow.LayoutParams((int)width, (int)height));
		} else {
			imageView = (CustomImageView)convertView;
		}
		FileContent fileContent = getFileContent(getItem(position));
		Uri uri = fileContent.getUri();
		imageView.setImageContent(ReflectedRemoteImageResolver.getReflectedUri(uri), getDefaultDrawableId());
		return imageView;
	}
	
	protected abstract FileContent getFileContent(T item);
	
	protected abstract int getDefaultDrawableId();
	
}
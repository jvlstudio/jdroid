package com.jdroid.android.coverflow;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.Gallery;
import android.widget.ImageView;

/**
 * Cover Flow implementation.
 * 
 * http://code.google.com/p/android-coverflow/
 */
public class CoverFlow extends Gallery {
	
	/**
	 * Graphics Camera used for transforming the matrix of ImageViews.
	 */
	private final Camera mCamera = new Camera();
	
	/**
	 * The maximum angle the Child ImageView will be rotated by.
	 */
	private int mMaxRotationAngle = 60;
	
	/**
	 * The maximum zoom on the centre Child.
	 */
	private int mMaxZoom = -120;
	
	/**
	 * The Centre of the Coverflow.
	 */
	private int mCoveflowCenter;
	
	public CoverFlow(final Context context) {
		super(context);
		this.setStaticTransformationsEnabled(true);
	}
	
	public CoverFlow(final Context context, final AttributeSet attrs) {
		this(context, attrs, android.R.attr.galleryStyle);
	}
	
	public CoverFlow(final Context context, final AttributeSet attrs, final int defStyle) {
		super(context, attrs, defStyle);
		setSpacing(-5);
		this.setStaticTransformationsEnabled(true);
	}
	
	/**
	 * Set the max rotational angle of each image.
	 * 
	 * @param maxRotationAngle the mMaxRotationAngle to set
	 */
	public void setMaxRotationAngle(final int maxRotationAngle) {
		mMaxRotationAngle = maxRotationAngle;
	}
	
	/**
	 * Set the max zoom of the centre image.
	 * 
	 * @param maxZoom the mMaxZoom to set
	 */
	public void setMaxZoom(final int maxZoom) {
		mMaxZoom = maxZoom;
	}
	
	/**
	 * Get the Centre of the Coverflow.
	 * 
	 * @return The centre of this Coverflow.
	 */
	private int getCenterOfCoverflow() {
		return ((getWidth() - getPaddingLeft() - getPaddingRight()) / 2) + getPaddingLeft();
	}
	
	/**
	 * Get the Centre of the View.
	 * 
	 * @return The centre of the given view.
	 */
	private static int getCenterOfView(final View view) {
		return view.getLeft() + (view.getWidth() / 2);
	}
	
	/**
	 * @see android.widget.Gallery#getChildStaticTransformation(android.view.View,
	 *      android.view.animation.Transformation)
	 */
	@Override
	protected boolean getChildStaticTransformation(final View child, final Transformation t) {
		
		int childCenter = getCenterOfView(child);
		int childWidth = child.getWidth();
		int rotationAngle = 0;
		
		t.clear();
		t.setTransformationType(Transformation.TYPE_MATRIX);
		
		if (childCenter == mCoveflowCenter) {
			transformImageBitmap((ImageView)child, t, 0);
		} else {
			rotationAngle = (int)(((float)(mCoveflowCenter - childCenter) / childWidth) * mMaxRotationAngle);
			if (Math.abs(rotationAngle) > mMaxRotationAngle) {
				rotationAngle = rotationAngle < 0 ? -mMaxRotationAngle : mMaxRotationAngle;
			}
			transformImageBitmap((ImageView)child, t, rotationAngle);
		}
		
		return true;
	}
	
	/**
	 * This is called during layout when the size of this view has changed. If you were just added to the view
	 * hierarchy, you're called with the old values of 0.
	 * 
	 * @param w Current width of this view.
	 * @param h Current height of this view.
	 * @param oldw Old width of this view.
	 * @param oldh Old height of this view.
	 * 
	 * @see android.view.View#onSizeChanged(int, int, int, int)
	 */
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		mCoveflowCenter = getCenterOfCoverflow();
		super.onSizeChanged(w, h, oldw, oldh);
	}
	
	/**
	 * Transform the Image Bitmap by the Angle passed.
	 * 
	 * @param imageView ImageView the ImageView whose bitmap we want to rotate
	 * @param t transformation
	 * @param rotationAngle the Angle by which to rotate the Bitmap
	 */
	private void transformImageBitmap(ImageView child, Transformation t, int rotationAngle) {
		mCamera.save();
		
		Matrix imageMatrix = t.getMatrix();
		int height = child.getLayoutParams().height;
		int width = child.getLayoutParams().width;
		int rotation = Math.abs(rotationAngle);
		
		mCamera.translate(0.0f, 0.0f, 100.0f);
		
		// As the angle of the view gets less, zoom in
		if (rotation < mMaxRotationAngle) {
			float zoomAmount = (float)(mMaxZoom + (rotation * 1.5));
			mCamera.translate(0.0f, 0.0f, zoomAmount);
		}
		
		mCamera.rotateY(rotationAngle);
		mCamera.getMatrix(imageMatrix);
		imageMatrix.preTranslate(-(width / 2.0f), -(height / 2.0f));
		imageMatrix.postTranslate((width / 2.0f), (height / 2.0f));
		mCamera.restore();
	}
}

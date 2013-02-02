package com.jdroid.android.utils;

import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;

public final class AnimationUtils {
	
	/**
	 * Private constructor to avoid instantiation
	 */
	private AnimationUtils() {
		// Do nothing...
	}
	
	public static void makeViewGroupAnimation(ViewGroup viewGroup) {
		AnimationSet set = new AnimationSet(true);
		
		Animation animation = new AlphaAnimation(0.0f, 1.0f);
		animation.setDuration(50);
		set.addAnimation(animation);
		
		animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
		animation.setDuration(150);
		set.addAnimation(animation);
		
		LayoutAnimationController controller = new LayoutAnimationController(set, 0.5f);
		viewGroup.setLayoutAnimation(controller);
	}
}
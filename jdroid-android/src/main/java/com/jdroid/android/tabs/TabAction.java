package com.jdroid.android.tabs;

import java.io.Serializable;
import android.app.Activity;
import android.support.v4.app.Fragment;

/**
 * 
 * @author Maxi Rosson
 */
public interface TabAction extends Serializable {
	
	public String getName();
	
	public int getIconResource();
	
	public int getNameResource();
	
	public Fragment createFragment(Object args);
	
	public Class<? extends Activity> getActivityClass();
}

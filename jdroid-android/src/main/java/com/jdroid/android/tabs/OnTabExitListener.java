package com.jdroid.android.tabs;

/**
 * Interface definition for a callback to be invoked when exit from a current selected tab in a TabHost.
 */
public interface OnTabExitListener {
	
	/**
	 * Called when exit from a current visible tab.
	 * 
	 * @param tag
	 */
	public void onTabExit(String tag);
	
}

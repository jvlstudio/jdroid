package com.jdroid.android.wizard;

import android.support.v4.app.Fragment;

/**
 * 
 * @author Maxi Rosson
 */
public abstract class WizardStep {
	
	public abstract Fragment createFragment(Object args);
}

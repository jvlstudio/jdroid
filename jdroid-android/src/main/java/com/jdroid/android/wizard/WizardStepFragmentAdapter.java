package com.jdroid.android.wizard;

import java.util.List;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * 
 * @author Maxi Rosson
 */
public class WizardStepFragmentAdapter extends FragmentPagerAdapter {
	
	private List<? extends WizardStep> wizardSteps;
	private Object fragmentArgs;
	
	public WizardStepFragmentAdapter(FragmentManager fm, List<? extends WizardStep> wizardSteps) {
		this(fm, wizardSteps, null);
	}
	
	public WizardStepFragmentAdapter(FragmentManager fm, List<? extends WizardStep> wizardSteps, Object fragmentArgs) {
		super(fm);
		this.wizardSteps = wizardSteps;
		this.fragmentArgs = fragmentArgs;
	}
	
	@Override
	public Fragment getItem(int i) {
		return wizardSteps.get(i).createFragment(fragmentArgs);
	}
	
	@Override
	public int getCount() {
		return wizardSteps.size();
	}
}
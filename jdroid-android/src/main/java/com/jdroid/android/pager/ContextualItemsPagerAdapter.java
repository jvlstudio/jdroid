package com.jdroid.android.pager;

import java.util.List;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.jdroid.android.tabs.TabAction;
import com.jdroid.android.utils.LocalizationUtils;

/**
 * 
 * @author Maxi Rosson
 */
public class ContextualItemsPagerAdapter extends FragmentPagerAdapter {
	
	private List<? extends TabAction> tabActions;
	private Object fragmentArgs;
	
	public ContextualItemsPagerAdapter(FragmentManager fm, List<? extends TabAction> tabActions, Object fragmentArgs) {
		super(fm);
		this.tabActions = tabActions;
		this.fragmentArgs = fragmentArgs;
	}
	
	@Override
	public Fragment getItem(int i) {
		return tabActions.get(i).createFragment(fragmentArgs);
	}
	
	@Override
	public int getCount() {
		return tabActions.size();
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		return LocalizationUtils.getString(tabActions.get(position).getNameResource());
	}
}

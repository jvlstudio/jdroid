package com.jdroid.android.fragment;

import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import com.jdroid.android.R;
import com.jdroid.android.adapter.BaseArrayAdapter;
import com.jdroid.android.listener.FilterListTextWatcher;

/**
 * Search fragment that performs the search only once, when the fragment is created, and has the ability to filter
 * thereafter.
 * 
 * @param <T> An item in the list. The filtering will be done based on {@link T#toString()}.
 * 
 * @author Estefania Caravatti
 */
public abstract class AbstractLocalSearchFragment<T> extends AbstractListFragment<T> {
	
	/**
	 * @see com.jdroid.android.fragment.AbstractListFragment#onViewCreated(android.view.View, android.os.Bundle)
	 */
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		getListView().getEmptyView().setVisibility(View.GONE);
		
		EditText searchText = findView(R.id.searchText);
		searchText.requestFocus();
		searchText.addTextChangedListener(getTextWatcher());
		
		TextView emptyLegend = findView(android.R.id.empty);
		emptyLegend.setText(getNoResultsResId());
		
		setListAdapter(createBaseArrayAdapter());
	}
	
	protected abstract int getNoResultsResId();
	
	protected abstract BaseArrayAdapter<T> createBaseArrayAdapter();
	
	protected TextWatcher getTextWatcher() {
		return new FilterListTextWatcher<T>() {
			
			@SuppressWarnings("unchecked")
			@Override
			public ArrayAdapter<T> getFilterableArrayAdapter() {
				return (BaseArrayAdapter<T>)getListAdapter();
			}
		};
	}
}

package com.jdroid.android.activity;

import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import com.jdroid.android.listener.FilterListTextWatcher;

/**
 * Search activity that performs the search only once, when the activity is created, and has the ability to filter
 * thereafter.
 * 
 * @param <T> An item in the list. The filtering will be done based on {@link T#toString()}.
 * 
 * @author Estefania Caravatti
 */
public abstract class AbstractLocalSearchActivity<T> extends AbstractSearchActivity<T> {
	
	/**
	 * @see com.jdroid.android.activity.AbstractSearchActivity#onStart()
	 */
	@Override
	protected void onStart() {
		super.onStart();
		searchText.addTextChangedListener(getTextWatcher());
		
		// The first time the activity is started, we perform a search to the server
		if (executeSearchOnStart() && getSearchUseCase().isNotInvoked()) {
			executeUseCase(getSearchUseCase());
		}
	}
	
	protected TextWatcher getTextWatcher() {
		return new FilterListTextWatcher<T>() {
			
			@Override
			public void doAfterTextChanged(String prefix) {
				AbstractLocalSearchActivity.this.afterTextChanged(prefix.toString());
			}
			
			@Override
			public ArrayAdapter<T> getFilterableArrayAdapter() {
				return AbstractLocalSearchActivity.this.getFilterableArrayAdapter();
			}
		};
	}
	
	protected Boolean executeSearchOnStart() {
		return true;
	}
	
	/**
	 * @see com.jdroid.android.activity.AbstractSearchActivity#doCancel()
	 */
	@Override
	protected void doCancel() {
		searchText.setText(null);
	}
	
	/**
	 * @see com.jdroid.android.activity.AbstractSearchActivity#displaySearchButton()
	 */
	@Override
	protected boolean displaySearchButton() {
		return false;
	}
	
	/**
	 * @see com.jdroid.android.activity.AbstractSearchActivity#doSearch()
	 */
	@Override
	protected void doSearch() {
		// Do Nothing
	}
	
	/**
	 * @return The {@link ArrayAdapter} used to filter the results.
	 */
	protected abstract ArrayAdapter<T> getFilterableArrayAdapter();
	
	protected void afterTextChanged(String text) {
		// Do Nothing
	}
}
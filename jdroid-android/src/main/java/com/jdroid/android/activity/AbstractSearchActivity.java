package com.jdroid.android.activity;

import roboguice.inject.InjectView;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.jdroid.android.R;
import com.jdroid.android.listener.OnEnterKeyListener;
import com.jdroid.android.search.PagedResult;
import com.jdroid.android.search.SearchResult;
import com.jdroid.android.usecase.SearchUseCase;
import com.jdroid.android.utils.ToastUtils;
import com.jdroid.java.utils.StringUtils;

/**
 * Base search activity. It has a search text and a list with the results.
 * 
 * @param <T> An item in the list.
 * 
 * @author Maxi Rosson
 */
public abstract class AbstractSearchActivity<T> extends AbstractListActivity<T> {
	
	protected EditText searchText;
	private ImageView searchButton;
	private ImageView cancelButton;
	
	@InjectView(android.R.id.empty)
	private TextView emptyLegend;
	
	/**
	 * @see com.jdroid.android.activity.ActivityIf#getContentView()
	 */
	@Override
	public int getContentView() {
		return R.layout.abstract_search_activity;
	}
	
	/**
	 * @see com.jdroid.android.activity.AbstractListActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		searchText = findView(R.id.searchText);
		searchButton = findView(R.id.searchButton);
		cancelButton = findView(R.id.cancelButton);
		
		getListView().getEmptyView().setVisibility(View.GONE);
		
		searchText.setOnKeyListener(new OnEnterKeyListener(false) {
			
			@Override
			public void onRun(View view) {
				search();
			}
		});
		searchText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
				}
			}
		});
		
		if (displaySearchButton()) {
			searchButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					search();
				}
			});
		} else {
			searchButton.setVisibility(View.GONE);
		}
		
		if (displayCancelButton()) {
			cancelButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					doCancel();
				}
			});
		} else {
			cancelButton.setVisibility(View.GONE);
		}
		
		emptyLegend.setText(getNoResultsResId());
	}
	
	protected int getNoResultsResId() {
		return R.string.noResultsSearch;
	}
	
	/**
	 * @see com.jdroid.android.activity.AbstractListActivity#onStart()
	 */
	@Override
	protected void onStart() {
		super.onStart();
		getSearchUseCase().addListener(this);
		if (getSearchUseCase().isInProgress()) {
			onStartUseCase();
		} else if (getSearchUseCase().isFinishSuccessful()) {
			onFinishUseCase();
		}
	}
	
	/**
	 * @see com.jdroid.android.activity.AbstractListActivity#onStop()
	 */
	@Override
	protected void onStop() {
		super.onStop();
		getSearchUseCase().removeListener(this);
	}
	
	private void search() {
		if (StringUtils.isNotEmpty(searchText.getText().toString()) || !isSearchValueRequired()) {
			doSearch();
		} else {
			ToastUtils.showToast(R.string.requiredSearchTerm);
		}
	}
	
	protected void doCancel() {
		getSearchUseCase().setSearchValue(null);
		executeUseCase(getSearchUseCase());
	}
	
	protected void doSearch() {
		getSearchUseCase().setSearchValue(searchText.getText().toString());
		executeUseCase(getSearchUseCase());
	}
	
	/**
	 * @see com.jdroid.android.activity.AbstractListActivity#onFinishUseCase()
	 */
	@Override
	public void onFinishUseCase() {
		executeOnUIThread(new Runnable() {
			
			@Override
			public void run() {
				SearchResult<T> searchResult = getSearchUseCase().getSearchResult();
				PagedResult<T> pagedResult = searchResult != null ? searchResult.getPagedResult()
						: new PagedResult<T>();
				BaseAdapter arrayAdapter = updateArrayAdapter(pagedResult);
				setListAdapter(arrayAdapter);
				arrayAdapter.notifyDataSetChanged();
				dismissLoading();
			}
		});
	}
	
	/**
	 * Updates the adapter with the given results.
	 * 
	 * @param pagedResult
	 * @return The updated adapter.
	 */
	protected abstract BaseAdapter updateArrayAdapter(PagedResult<T> pagedResult);
	
	/**
	 * @return The {@link SearchUseCase} to use.
	 */
	protected abstract SearchUseCase<T> getSearchUseCase();
	
	protected boolean displaySearchButton() {
		return true;
	}
	
	protected boolean displayCancelButton() {
		return true;
	}
	
	/**
	 * @return Whether a search can be performed without a search value or not.
	 */
	public boolean isSearchValueRequired() {
		return false;
	}
	
}

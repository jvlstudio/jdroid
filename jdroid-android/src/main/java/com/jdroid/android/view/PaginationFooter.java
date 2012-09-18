package com.jdroid.android.view;

import java.util.List;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.jdroid.android.R;
import com.jdroid.android.activity.AbstractListActivity;
import com.jdroid.android.adapter.BaseArrayAdapter;
import com.jdroid.android.search.SearchResult.PaginationListener;
import com.jdroid.java.exception.AbstractException;

/**
 * 
 * @author Maxi Rosson
 */
public class PaginationFooter extends LinearLayout implements PaginationListener<Object> {
	
	private AbstractListActivity<?> abstractListActivity;
	private Boolean loading = false;
	
	/**
	 * @param context
	 * @param attrs
	 */
	public PaginationFooter(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	/**
	 * @see android.widget.TextView#onDraw(android.graphics.Canvas)
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (!loading && !abstractListActivity.getSearchResult().isLastPage()) {
			loading = true;
			abstractListActivity.getSearchResult().nextPage();
		}
	}
	
	/**
	 * @see com.jdroid.android.search.SearchResult.PaginationListener#onStartPagination()
	 */
	@Override
	public void onStartPagination() {
		// Do Nothing
	}
	
	/**
	 * @see com.jdroid.android.search.SearchResult.PaginationListener#onFinishSuccessfulPagination(java.util.List)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void onFinishSuccessfulPagination(final List<Object> items) {
		refresh();
		abstractListActivity.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				BaseArrayAdapter<Object> baseArrayAdapter = (BaseArrayAdapter<Object>)abstractListActivity.getListAdapter();
				baseArrayAdapter.addAll(items);
			}
		});
		loading = false;
	}
	
	public void refresh() {
		abstractListActivity.executeOnUIThread(new Runnable() {
			
			@Override
			public void run() {
				if (abstractListActivity.getSearchResult().isLastPage()) {
					hide();
				} else {
					show();
				}
			}
		});
	}
	
	public void hide() {
		// REVIEW: see if we can hide the whole footer
		findViewById(R.id.progressBar).setVisibility(View.GONE);
		findViewById(R.id.loading).setVisibility(View.GONE);
	}
	
	private void show() {
		findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
		findViewById(R.id.loading).setVisibility(View.VISIBLE);
	}
	
	/**
	 * @see com.jdroid.android.search.SearchResult.PaginationListener#onFinishInvalidPagination(com.jdroid.java.exception.AbstractException)
	 */
	@Override
	public void onFinishInvalidPagination(AbstractException androidException) {
		refresh();
		loading = false;
		throw androidException;
	}
	
	/**
	 * @param abstractListActivity the abstractListActivity to set
	 */
	public void setAbstractListActivity(AbstractListActivity<?> abstractListActivity) {
		this.abstractListActivity = abstractListActivity;
	}
}

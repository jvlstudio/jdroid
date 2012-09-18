package com.jdroid.android.listener;

import android.view.View;
import android.view.View.OnClickListener;
import com.jdroid.android.usecase.DefaultUseCase;
import com.jdroid.java.utils.ExecutorUtils;

/**
 * {@link OnClickListener} that execute a {@link DefaultUseCase}
 * 
 * @author Maxi Rosson
 */
public class UseCaseOnClickListener implements OnClickListener {
	
	private DefaultUseCase<?> defaultUseCase;
	
	/**
	 * @param defaultUseCase The {@link DefaultUseCase} to execute
	 */
	public UseCaseOnClickListener(DefaultUseCase<?> defaultUseCase) {
		this.defaultUseCase = defaultUseCase;
	}
	
	/**
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public final void onClick(final View view) {
		ExecutorUtils.execute(defaultUseCase);
	}
	
}

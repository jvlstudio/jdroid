package com.jdroid.android.loading;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.jdroid.android.R;

/**
 * {@link Dialog} that displays a Loading text and a {@link ProgressBar} on indeterminate mode
 * 
 */
public class LoadingDialog extends Dialog {
	
	private TextView loadingText;
	
	/**
	 * Constructor
	 * 
	 * @param context The Context in which the Dialog should run.
	 */
	public LoadingDialog(Context context) {
		this(context, R.style.customDialog);
	}
	
	/**
	 * Constructor
	 * 
	 * @param context The Context in which the Dialog should run.
	 * @param theme A style resource describing the theme to use for the window.
	 */
	public LoadingDialog(Context context, int theme) {
		super(context, theme);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.loading_dialog);
		loadingText = (TextView)findViewById(R.id.loadingText);
	}
	
	public void setLoadingText(int loadingResId) {
		loadingText.setText(loadingResId);
	}
}

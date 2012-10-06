package com.jdroid.android.view;

import java.util.Date;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import com.jdroid.android.fragment.TimePickerDialogFragment;
import com.jdroid.android.utils.AndroidDateUtils;

/**
 * 
 * @author Maxi Rosson
 */
public class TimeEditText extends EditText {
	
	/**
	 * @param context
	 * @param attrs
	 */
	public TimeEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public void init(final Fragment fragment, final Date defaultTime) {
		setTime(defaultTime);
		setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TimePickerDialogFragment.show(fragment, defaultTime);
			}
		});
		setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					TimePickerDialogFragment.show(fragment, defaultTime);
				}
			}
		});
		setLongClickable(false);
	}
	
	public void setTime(Date defaultTime) {
		setText(AndroidDateUtils.formatTime(defaultTime));
	}
}

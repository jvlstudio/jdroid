package com.jdroid.android.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;
import com.jdroid.android.R;

/**
 * Text view used to show a badge with a number of notifications. If the notifications are less than zero, the badge
 * isn't visible.
 * 
 * @author Estefania Caravatti
 */
public class BadgeView extends TextView {
	
	private static final int DEFAULT_MAXIMUM = 9;
	private int maximum = DEFAULT_MAXIMUM;
	
	public BadgeView(Context context) {
		this(context, null);
	}
	
	/**
	 * @param context
	 * @param attrs
	 */
	public BadgeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		if (isInEditMode()) {
			setText("1");
		} else {
			// hide by default
			setVisibility(GONE);
		}
		
		// Adding styles
		setBackgroundResource(R.drawable.badge);
		setTextColor(Color.WHITE);
		setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
		setTypeface(Typeface.DEFAULT_BOLD);
	}
	
	/**
	 * Sets a notification number in the badge.
	 * 
	 * @param notifications
	 */
	public void setNotifications(Integer notifications) {
		
		if ((notifications != null) && (notifications > 0)) {
			setVisibility(VISIBLE);
			if (notifications > maximum) {
				this.setText(maximum + "+");
			} else {
				this.setText(notifications.toString());
			}
		} else {
			setVisibility(GONE);
		}
	}
	
	/**
	 * @param maximum the maximum to set
	 */
	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}
	
}

package com.jdroid.android.view;

import java.util.Date;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import com.jdroid.android.R;
import com.jdroid.java.utils.DateUtils;

/**
 * 
 * @author Maxi Rosson
 */
public class DateEditText extends EditText {
	
	private static final int DATE_DIALOG_ID = 0;
	
	private Activity activity;
	private Date date;
	
	/**
	 * @param context
	 * @param attrs
	 */
	public DateEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@SuppressWarnings("deprecation")
	public void init(final Activity activity, Date defaultDate) {
		this.activity = activity;
		setDate(defaultDate);
		setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				activity.showDialog(DATE_DIALOG_ID);
			}
		});
		setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					activity.showDialog(DATE_DIALOG_ID);
				}
			}
		});
		setLongClickable(false);
	}
	
	public void setDate(Date date) {
		this.date = date;
		setText(DateUtils.format(date, DateUtils.MMDDYYYY_SLASH_DATE_FORMAT));
	}
	
	/**
	 * @return the activity
	 */
	public Activity getActivity() {
		return activity;
	}
	
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	
	public static Dialog onCreateDialog(final DateEditText dateEditText, int id) {
		switch (id) {
			case DATE_DIALOG_ID:
				OnDateSetListener dateSetListener = new OnDateSetListener() {
					
					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
						dateEditText.setDate(DateUtils.getDate(year, monthOfYear, dayOfMonth));
					}
				};
				return new DatePickerDialog(dateEditText.activity, R.style.customDialog, dateSetListener,
						DateUtils.getYear(dateEditText.date), DateUtils.getMonth(dateEditText.date),
						DateUtils.getDay(dateEditText.date));
		}
		return null;
	}
	
	public static Boolean onPrepareDialog(final DateEditText dateEditText, int id, Dialog dialog) {
		
		switch (id) {
			case DATE_DIALOG_ID:
				((DatePickerDialog)dialog).updateDate(DateUtils.getYear(dateEditText.date),
					DateUtils.getMonth(dateEditText.date), DateUtils.getDay(dateEditText.date));
				return true;
			default:
				return false;
		}
	}
}

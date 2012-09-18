package com.jdroid.android.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Handler;
import com.jdroid.android.R;
import com.jdroid.android.AbstractApplication;

public final class AlertDialogUtils {
	
	private static final int DIALOG = 2;
	private static final int BUILDER = 3;
	
	private static final Handler HANDLER = new Handler() {
		
		@Override
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
				case DIALOG:
					AlertDialog dialog = (AlertDialog)msg.obj;
					dialog.show();
					break;
				case BUILDER:
					AlertDialog.Builder builder = (Builder)msg.obj;
					builder.create().show();
					break;
			}
		};
	};
	
	public static final void init() {
		// nothing...
	}
	
	/**
	 * @param builder
	 */
	public static void show(AlertDialog.Builder builder) {
		HANDLER.sendMessage(HANDLER.obtainMessage(BUILDER, builder));
	}
	
	/**
	 * @param alertDialog
	 */
	public static void show(AlertDialog alertDialog) {
		HANDLER.sendMessage(HANDLER.obtainMessage(DIALOG, alertDialog));
	}
	
	public static void showOKDialog(int titleResId, int messageResId) {
		showOKDialog(LocalizationUtils.getString(titleResId), LocalizationUtils.getString(messageResId), null,
			LocalizationUtils.getString(R.string.ok));
	}
	
	public static void showOKDialog(int titleResId, int messageResId, DialogInterface.OnClickListener onClickListener) {
		showOKDialog(LocalizationUtils.getString(titleResId), LocalizationUtils.getString(messageResId),
			onClickListener, LocalizationUtils.getString(R.string.ok));
	}
	
	public static void showOKDialog(String title, String message, DialogInterface.OnClickListener onClickListener,
			String buttonText) {
		Activity activity = AbstractApplication.get().getCurrentActivity();
		AlertDialog.Builder downloadDialog = new AlertDialog.Builder(activity);
		downloadDialog.setTitle(title);
		downloadDialog.setMessage(message);
		downloadDialog.setPositiveButton(buttonText, onClickListener);
		AlertDialogUtils.show(downloadDialog);
	}
	
	public static void showUnsavedChangesDialog(OnClickListener onClickListener) {
		final Activity activity = AbstractApplication.get().getCurrentActivity();
		DialogInterface.OnClickListener dialogClickListener = onClickListener;
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setTitle(R.string.areYouSure);
		builder.setMessage(R.string.confirmExit);
		builder.setPositiveButton(R.string.yes, dialogClickListener);
		builder.setNegativeButton(R.string.no, null);
		builder.show();
	}
	
	public static void showUnsavedChangesDialog() {
		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				AbstractApplication.get().getCurrentActivity().finish();
			}
		};
		showUnsavedChangesDialog(dialogClickListener);
	}
}

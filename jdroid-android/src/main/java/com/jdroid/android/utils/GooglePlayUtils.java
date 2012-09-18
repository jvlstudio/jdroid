package com.jdroid.android.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import com.jdroid.android.R;
import com.jdroid.android.AbstractApplication;

/**
 * 
 * @author Maxi Rosson
 */
public class GooglePlayUtils {
	
	public static void showUpdateDialog() {
		
		final Context context = AbstractApplication.get().getCurrentActivity();
		AlertDialog.Builder downloadDialog = new AlertDialog.Builder(context);
		String appName = AndroidUtils.getApplicationName();
		downloadDialog.setTitle(context.getString(R.string.updateAppTitle, appName));
		downloadDialog.setMessage(context.getString(R.string.updateAppMessage, appName));
		downloadDialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
				Uri uri = Uri.parse("market://details?id=" + AndroidUtils.getPackageName());
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				context.startActivity(intent);
			}
		});
		AlertDialogUtils.show(downloadDialog);
	}
	
	public static AlertDialog showDownloadDialog(final Activity activity, int appNameResId, final String packageName) {
		AlertDialog.Builder downloadDialog = new AlertDialog.Builder(activity);
		String appName = activity.getString(appNameResId);
		downloadDialog.setTitle(activity.getString(R.string.installAppTitle, appName));
		downloadDialog.setMessage(activity.getString(R.string.installAppMessage, appName));
		downloadDialog.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
				launchAppDetails(activity, packageName);
			}
		});
		downloadDialog.setNegativeButton(activity.getString(R.string.no), new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
			}
		});
		
		return downloadDialog.show();
	}
	
	public static void launchAppDetails(Activity activity, String packageName) {
		Uri uri = Uri.parse("market://details?id=" + packageName);
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		activity.startActivity(intent);
	}
}

package com.jdroid.android.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;
import roboguice.service.RoboIntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.jdroid.android.R;
import com.google.inject.Inject;
import com.jdroid.android.AbstractApplication;
import com.jdroid.android.context.ErrorReportingContext;
import com.jdroid.android.utils.AndroidUtils;
import com.jdroid.android.utils.IntentRetryUtils;
import com.jdroid.android.utils.NotificationUtils;
import com.jdroid.java.mail.MailException;
import com.jdroid.java.mail.MailService;
import com.jdroid.java.utils.DateUtils;
import com.jdroid.java.utils.IdGenerator;

/**
 * 
 * @author Maxi Rosson
 */
public class ExceptionReportService extends RoboIntentService {
	
	private static final String TAG = ExceptionReportService.class.getSimpleName();
	
	private static final String EXTRA_STACK_TRACE = "stackTrace";
	private static final String EXTRA_EXCEPTION_TIME = "exceptionTime";
	private static final String EXTRA_THREAD_NAME = "threadName";
	
	private static final String NEW_LINE = "\n";
	
	@Inject
	private MailService mailService;
	
	public ExceptionReportService() {
		super(TAG);
	}
	
	@Override
	protected void onHandleIntent(Intent intent) {
		try {
			sendReport(intent);
		} catch (Exception e) {
			Log.e(TAG, "Error while sending an error report", e);
		}
	}
	
	private void sendReport(Intent intent) {
		Log.v(TAG, "Got request to report error: " + intent.toString());
		
		try {
			StringBuilder builder = new StringBuilder();
			builder.append("Date: ");
			builder.append(intent.getStringExtra(EXTRA_EXCEPTION_TIME));
			builder.append(NEW_LINE);
			
			builder.append("Thread Name: ");
			builder.append(intent.getStringExtra(EXTRA_THREAD_NAME));
			builder.append(NEW_LINE);
			
			builder.append("App Version Code: ");
			builder.append(AndroidUtils.getVersionCode());
			builder.append(NEW_LINE);
			
			String versionName = AndroidUtils.getVersionName();
			builder.append("App Version Name: ");
			builder.append(versionName);
			builder.append(NEW_LINE);
			
			String packageName = AndroidUtils.getPackageName();
			builder.append("App Package Name: ");
			builder.append(packageName);
			builder.append(NEW_LINE);
			
			builder.append("Available Data: ");
			builder.append(AndroidUtils.getAvailableInternalDataSize());
			builder.append(" MB");
			builder.append(NEW_LINE);
			
			builder.append("Total Data: ");
			builder.append(AndroidUtils.getTotalInternalDataSize());
			builder.append(" MB");
			builder.append(NEW_LINE);
			
			builder.append("Heap Size: ");
			builder.append(AndroidUtils.getHeapSize());
			builder.append(" MB");
			builder.append(NEW_LINE);
			
			builder.append("Device Model: ");
			builder.append(AndroidUtils.getDeviceModel());
			builder.append(NEW_LINE);
			
			builder.append("API Level: ");
			builder.append(AndroidUtils.getApiLevel());
			builder.append(NEW_LINE);
			
			builder.append("Platform Version: ");
			builder.append(AndroidUtils.getPlatformVersion());
			builder.append(NEW_LINE);
			builder.append(NEW_LINE);
			
			builder.append(intent.getStringExtra(EXTRA_STACK_TRACE));
			builder.append(NEW_LINE);
			
			mailService.sendMail("[Android Error] " + packageName + " v" + versionName, builder.toString(),
				ErrorReportingContext.get().getMailFrom(), ErrorReportingContext.get().getMailTo());
			
		} catch (MailException e) {
			Log.e(TAG, "Unexepected error when sending the email. Retrying later.", e);
			IntentRetryUtils.retry(intent);
		}
	}
	
	/**
	 * Sends an error report.
	 * 
	 * @param thread The thread where the exception occurred (e.g. {@link java.lang.Thread#currentThread()})
	 * @param ex The exception
	 */
	public static void reportException(Thread thread, Throwable ex) {
		
		try {
			Context context = AbstractApplication.get();
			
			Writer writer = new StringWriter();
			ex.printStackTrace(new PrintWriter(writer));
			
			Date now = DateUtils.now();
			Bundle bundle = new Bundle();
			bundle.putString(EXTRA_THREAD_NAME, thread.getName());
			bundle.putString(EXTRA_EXCEPTION_TIME, DateUtils.format(now, DateUtils.YYYYMMDDHHMMSSZ_DATE_FORMAT));
			bundle.putString(EXTRA_STACK_TRACE, writer.toString());
			
			String notificationTitle = context.getString(R.string.exceptionReportNotificationTitle,
				AndroidUtils.getApplicationName());
			String notificationText = context.getString(R.string.exceptionReportNotificationText);
			
			NotificationUtils.sendNotification(IdGenerator.getRandomIntId(), android.R.drawable.stat_notify_error,
				notificationTitle, notificationTitle, notificationText, ExceptionReportActivity.class, now.getTime(),
				bundle);
		} catch (Exception e) {
			Log.e(TAG, "Unexepected error from the exception reporter", e);
		}
	}
}

package com.jdroid.android.exception;

import java.lang.Thread.UncaughtExceptionHandler;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.Log;
import com.jdroid.android.R;
import com.jdroid.android.AbstractApplication;
import com.jdroid.android.context.ErrorReportingContext;
import com.jdroid.android.utils.AlertDialogUtils;
import com.jdroid.android.utils.AndroidUtils;
import com.jdroid.android.utils.LocalizationUtils;
import com.jdroid.android.utils.ToastUtils;
import com.jdroid.java.exception.ApplicationException;
import com.jdroid.java.exception.BusinessException;
import com.jdroid.java.exception.ConnectionException;

/**
 * 
 * @author Maxi Rosson
 */
public class DefaultExceptionHandler implements ExceptionHandler {
	
	private static final String TAG = DefaultExceptionHandler.class.getSimpleName();
	private static final String MAIN_THREAD_NAME = "main";
	
	private UncaughtExceptionHandler defaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
	
	/**
	 * @see java.lang.Thread.UncaughtExceptionHandler#uncaughtException(java.lang.Thread, java.lang.Throwable)
	 */
	@Override
	public void uncaughtException(Thread thread, Throwable throwable) {
		
		Boolean mainThread = thread.getName().equals(MAIN_THREAD_NAME);
		
		if (mainThread) {
			handleMainThreadException(thread, throwable);
		} else if (throwable instanceof BusinessException) {
			handleException(thread, (BusinessException)throwable);
		} else if (throwable instanceof ConnectionException) {
			handleException(thread, (ConnectionException)throwable);
		} else if (throwable instanceof ApplicationException) {
			handleException(thread, (ApplicationException)throwable);
		} else if (!doUncaughtException(thread, throwable)) {
			handleException(thread, throwable);
		}
	}
	
	public Boolean doUncaughtException(Thread thread, Throwable throwable) {
		return false;
	}
	
	/**
	 * @see com.jdroid.android.exception.ExceptionHandler#handleException(com.jdroid.java.exception.BusinessException)
	 */
	@Override
	public void handleException(BusinessException businessException) {
		handleException(Thread.currentThread(), businessException);
	}
	
	/**
	 * @see com.jdroid.android.exception.ExceptionHandler#handleException(java.lang.Thread,
	 *      com.jdroid.java.exception.BusinessException)
	 */
	@Override
	public void handleException(Thread thread, BusinessException businessException) {
		String message = businessException.getMessage();
		if (businessException.getErrorCode() != null) {
			message = LocalizationUtils.getMessageFor(businessException.getErrorCode(),
				businessException.getErrorCodeParameters());
		}
		ToastUtils.showToastOnUIThread(message);
	}
	
	/**
	 * @see com.jdroid.android.exception.ExceptionHandler#handleException(java.lang.Thread,
	 *      com.jdroid.java.exception.ConnectionException)
	 */
	@Override
	public void handleException(Thread thread, ConnectionException connectionException) {
		Log.w(TAG, "Connection error", connectionException);
		ToastUtils.showToastOnUIThread(R.string.connectionError);
	}
	
	/**
	 * @see com.jdroid.android.exception.ExceptionHandler#handleException(java.lang.Thread,
	 *      com.jdroid.java.exception.ApplicationException)
	 */
	@Override
	public void handleException(Thread thread, ApplicationException applicationException) {
		String message = LocalizationUtils.getMessageFor(applicationException.getErrorCode());
		Log.e(TAG, message, applicationException);
		ToastUtils.showToastOnUIThread(message);
	}
	
	private void handleMainThreadException(Thread thread, Throwable throwable) {
		if (ErrorReportingContext.get().isMailReportingEnabled()) {
			ExceptionReportService.reportException(thread, throwable);
		}
		defaultExceptionHandler.uncaughtException(thread, throwable);
	}
	
	private void handleException(Thread thread, Throwable throwable) {
		Log.e(TAG, "Unexepected error", throwable);
		if (ErrorReportingContext.get().isMailReportingEnabled()) {
			ExceptionReportService.reportException(thread, throwable);
		}
		
		Activity activity = AbstractApplication.get().getCurrentActivity();
		if (activity != null) {
			AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
			dialog.setTitle(activity.getString(R.string.exceptionReportDialogTitle, AndroidUtils.getApplicationName()));
			dialog.setMessage(R.string.serverError);
			dialog.setCancelable(false);
			dialog.setPositiveButton(R.string.ok, new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Activity activity = AbstractApplication.get().getCurrentActivity();
					if (activity != null) {
						activity.finish();
					}
				}
			});
			AlertDialogUtils.show(dialog);
		}
	}
}

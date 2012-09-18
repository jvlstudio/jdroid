package com.jdroid.android.listener;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import com.jdroid.java.utils.ExecutorUtils;

public abstract class RemoteSearchTextWatcher implements TextWatcher {
	
	private static final String TAG = RemoteSearchTextWatcher.class.getSimpleName();
	
	// The search frequency in milliseconds
	private static final int SEARCH_FREQUENCY = 1000;
	
	private TimerSearchRunnable timerSearchRunnable;
	private Thread timerSearchThread;
	
	public RemoteSearchTextWatcher() {
		startWatching();
	}
	
	public void startWatching() {
		timerSearchRunnable = new TimerSearchRunnable();
		timerSearchThread = new Thread(timerSearchRunnable);
		timerSearchThread.start();
	}
	
	public void stopWatching() {
		timerSearchThread.interrupt();
	}
	
	/**
	 * @see android.text.TextWatcher#afterTextChanged(android.text.Editable)
	 */
	@Override
	public void afterTextChanged(Editable s) {
		try {
			timerSearchRunnable.queue.put(s.toString());
		} catch (InterruptedException e) {
			Log.e(TAG, e.getMessage());
		}
	}
	
	/**
	 * @see android.text.TextWatcher#beforeTextChanged(java.lang.CharSequence, int, int, int)
	 */
	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		// Nothing here by default.
	}
	
	/**
	 * @see android.text.TextWatcher#onTextChanged(java.lang.CharSequence, int, int, int)
	 */
	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// Nothing here by default.
	}
	
	protected abstract void onPerformSearch(String searchValue);
	
	private class TimerSearchRunnable implements Runnable {
		
		private BlockingQueue<String> queue = new ArrayBlockingQueue<String>(20);
		
		@Override
		public void run() {
			Boolean exit = false;
			while (!exit) {
				try {
					String searchValue = queue.take();
					if (queue.isEmpty()) {
						ExecutorUtils.sleepInMillis(SEARCH_FREQUENCY);
						// If after waiting for a SEARCH_FREQUENCY, no more input was added, the search is performed.
						if (queue.isEmpty()) {
							RemoteSearchTextWatcher.this.onPerformSearch(searchValue);
						}
					}
				} catch (InterruptedException e) {
					exit = true;
				}
			}
		}
	}
	
}

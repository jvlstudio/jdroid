package com.jdroid.android.usecase;

import java.util.List;
import com.jdroid.java.collections.Lists;

/**
 * 
 * @param <T> The Listener type
 * 
 * @author Maxi Rosson
 */
public abstract class AbstractUseCase<T> {
	
	public enum UseCaseStatus {
		NOT_INVOKED,
		IN_PROGRESS,
		FINISHED_SUCCESSFUL,
		FINISHED_FAILED,
		CANCELED;
	}
	
	private List<T> listeners = Lists.newArrayList();
	private UseCaseStatus useCaseStatus = UseCaseStatus.NOT_INVOKED;
	private RuntimeException runtimeException;
	private Boolean notified = false;
	
	/**
	 * @return the listeners
	 */
	protected List<T> getListeners() {
		return listeners;
	}
	
	/**
	 * @param listener the listener to add
	 */
	public void addListener(T listener) {
		if (!listeners.contains(listener)) {
			this.listeners.add(listener);
		}
	}
	
	/**
	 * @param listener the listener to remove
	 */
	public void removeListener(T listener) {
		this.listeners.remove(listener);
	}
	
	public Boolean isNotInvoked() {
		return UseCaseStatus.NOT_INVOKED.equals(useCaseStatus);
	}
	
	public Boolean isInProgress() {
		return UseCaseStatus.IN_PROGRESS.equals(useCaseStatus);
	}
	
	public Boolean isFinishSuccessful() {
		return UseCaseStatus.FINISHED_SUCCESSFUL.equals(useCaseStatus);
	}
	
	public Boolean isFinishFailed() {
		return UseCaseStatus.FINISHED_FAILED.equals(useCaseStatus);
	}
	
	/**
	 * @return If the use case has finished, regardless of the success or failure of its execution.
	 */
	public Boolean isFinish() {
		return (isFinishFailed() || isFinishSuccessful());
	}
	
	public Boolean isCanceled() {
		return UseCaseStatus.CANCELED.equals(useCaseStatus);
	}
	
	public void markAsNotified() {
		notified = true;
	}
	
	protected void markAsInProgress() {
		notified = false;
		runtimeException = null;
		this.useCaseStatus = UseCaseStatus.IN_PROGRESS;
	}
	
	protected void markAsSuccessful() {
		this.useCaseStatus = UseCaseStatus.FINISHED_SUCCESSFUL;
	}
	
	protected void markAsFailed(RuntimeException runtimeException) {
		this.useCaseStatus = UseCaseStatus.FINISHED_FAILED;
		this.runtimeException = runtimeException;
	}
	
	public void cancel() {
		this.useCaseStatus = UseCaseStatus.CANCELED;
	}
	
	public RuntimeException getRuntimeException() {
		return runtimeException;
	}
	
	public Boolean isNotified() {
		return notified;
	}
}

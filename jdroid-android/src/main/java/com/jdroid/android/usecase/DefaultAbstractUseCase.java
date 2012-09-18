package com.jdroid.android.usecase;

import com.jdroid.android.usecase.listener.DefaultUseCaseListener;

/**
 * Abstract use case that handles the calls to {@link DefaultUseCaseListener#onStartUseCase()},
 * {@link DefaultUseCaseListener#onFinishUseCase()} and
 * {@link DefaultUseCaseListener#onFinishFailedUseCase(RuntimeException)} when executing.
 * 
 * @author Maxi Rosson
 */
public abstract class DefaultAbstractUseCase extends AbstractUseCase<DefaultUseCaseListener> implements
		DefaultUseCase<DefaultUseCaseListener> {
	
	/**
	 * Executes the use case.
	 */
	@Override
	public final void run() {
		
		markAsInProgress();
		for (DefaultUseCaseListener listener : getListeners()) {
			listener.onStartUseCase();
		}
		try {
			doExecute();
			if (isCanceled()) {
				for (DefaultUseCaseListener listener : getListeners()) {
					listener.onFinishCanceledUseCase();
				}
			} else {
				for (DefaultUseCaseListener listener : getListeners()) {
					listener.onFinishUseCase();
				}
				markAsSuccessful();
			}
		} catch (RuntimeException e) {
			markAsFailed(e);
			for (DefaultUseCaseListener listener : getListeners()) {
				listener.onFinishFailedUseCase(e);
			}
		} finally {
			if (!getListeners().isEmpty()) {
				markAsNotified();
			}
		}
	}
	
	/**
	 * Override this method with the login to be executed between {@link NoResultUseCaseListener#onStartUseCase()} and
	 * before {@link NoResultUseCaseListener#onFinishUseCase()}
	 */
	protected abstract void doExecute();
}

package com.jdroid.android.repository;

import java.util.Collection;
import com.jdroid.java.domain.Identifiable;
import com.jdroid.java.repository.InMemoryRepository;

/**
 * 
 * @param <T>
 * @author Maxi Rosson
 */
public class SynchronizedInMemoryRepository<T extends Identifiable> extends InMemoryRepository<T> implements
		SynchronizedRepository<T> {
	
	// Default Refresh frequency (in milliseconds)
	private static final Long DEFAULT_REFRESH_FREQUENCY = 5 * 60000L; // 5 min
	
	private Long lastUpdateTimestamp;
	
	/**
	 * @see com.jdroid.java.repository.Repository#add(com.jdroid.java.domain.Identifiable)
	 */
	@Override
	public void add(T item) {
		super.add(item);
		refreshUpdateTimestamp();
	}
	
	/**
	 * @see com.jdroid.java.repository.Repository#addAll(java.util.Collection)
	 */
	@Override
	public void addAll(Collection<T> items) {
		super.addAll(items);
		refreshUpdateTimestamp();
	}
	
	/**
	 * @see com.jdroid.java.repository.Repository#remove(java.lang.Long)
	 */
	@Override
	public void remove(Long id) {
		super.remove(id);
		refreshUpdateTimestamp();
	}
	
	/**
	 * @see com.jdroid.java.repository.Repository#removeAll()
	 */
	@Override
	public void removeAll() {
		super.removeAll();
		resetLastUpdateTimestamp();
	}
	
	/**
	 * @see com.jdroid.java.repository.Repository#remove(com.jdroid.java.domain.Identifiable)
	 */
	@Override
	public void remove(T item) {
		super.remove(item);
		refreshUpdateTimestamp();
	};
	
	/**
	 * @see com.jdroid.java.repository.InMemoryRepository#replaceAll(java.util.Collection)
	 */
	@Override
	public void replaceAll(Collection<T> items) {
		super.removeAll();
		super.addAll(items);
		refreshUpdateTimestamp();
	}
	
	protected void refreshUpdateTimestamp() {
		lastUpdateTimestamp = System.currentTimeMillis();
	}
	
	/**
	 * @see com.jdroid.android.repository.SynchronizedRepository#isOutdated()
	 */
	@Override
	public Boolean isOutdated() {
		if ((lastUpdateTimestamp == null)
				|| ((lastUpdateTimestamp + getRefreshFrequency()) < System.currentTimeMillis())) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @see com.jdroid.android.repository.SynchronizedRepository#getLastUpdateTimestamp()
	 */
	@Override
	public Long getLastUpdateTimestamp() {
		return lastUpdateTimestamp;
	}
	
	/**
	 * @see com.jdroid.android.repository.SynchronizedRepository#resetLastUpdateTimestamp()
	 */
	@Override
	public void resetLastUpdateTimestamp() {
		lastUpdateTimestamp = null;
	}
	
	protected Long getRefreshFrequency() {
		return DEFAULT_REFRESH_FREQUENCY;
	}
}

package com.jdroid.javaweb.push;

import java.util.Collection;
import com.jdroid.java.repository.Repository;

/**
 * 
 * @author Maxi Rosson
 */
public interface DeviceRepository extends Repository<Device> {
	
	public Device find(Long userId, String installationId);
	
	public Collection<Device> findByUserId(Long userId);
	
}
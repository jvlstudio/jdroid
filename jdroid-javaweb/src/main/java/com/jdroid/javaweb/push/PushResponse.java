package com.jdroid.javaweb.push;

import java.util.List;
import com.google.common.collect.Lists;

/**
 * 
 * @author Maxi Rosson
 */
public class PushResponse {
	
	private List<Device> devicesToRemove;
	private List<Device> devicesToUpdate;
	
	public PushResponse() {
		devicesToRemove = Lists.newArrayList();
		devicesToUpdate = Lists.newArrayList();
	}
	
	public void addDeviceToRemove(Device device) {
		devicesToRemove.add(device);
	}
	
	public void addDeviceToUpdate(Device device) {
		devicesToUpdate.add(device);
	}
	
	public List<Device> getDevicesToRemove() {
		return devicesToRemove;
	}
	
	public List<Device> getDevicesToUpdate() {
		return devicesToUpdate;
	}
	
}

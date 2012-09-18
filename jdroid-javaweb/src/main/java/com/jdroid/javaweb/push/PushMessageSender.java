package com.jdroid.javaweb.push;

import java.util.List;

/**
 * 
 * @author Maxi Rosson
 */
public interface PushMessageSender {
	
	public PushResponse send(List<Device> devices, PushMessage pushMessage);
	
}
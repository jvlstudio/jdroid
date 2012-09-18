package com.jdroid.javaweb.push.gcm;

import com.jdroid.javaweb.push.PushMessage;

/**
 * 
 * @author Maxi Rosson
 */
public interface GcmMessage extends PushMessage {
	
	/**
	 * An arbitrary string that is used to collapse a group of like messages when the device is offline, so that only
	 * the last message gets sent to the client. This is intended to avoid sending too many messages to the phone when
	 * it comes back online. Note that since there is no guarantee of the order in which messages get sent, the "last"
	 * message may not actually be the last message sent by the application server. Optional, unless you are using the
	 * time_to_live parameter—in that case, you must also specify a collapse_key.
	 * 
	 * @return the collapseKey
	 */
	public String getCollapseKey();
	
	/**
	 * @return If true, indicates that the message should not be sent immediately if the device is idle. The server will
	 *         wait for the device to become active, and then only the last message for each collapseKey value will be
	 *         sent. Optional. The default value is false.
	 */
	public Boolean isDelayWhileIdle();
	
	/**
	 * @return How long (in seconds) the message should be kept on GCM storage if the device is offline. Optional
	 *         (default time-to-live is 4 weeks, the maximum possible value). If you use this parameter, you must also
	 *         specify a collapse_key.
	 */
	public Integer getTimeToLive();
	
}

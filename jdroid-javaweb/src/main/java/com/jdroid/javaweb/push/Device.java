package com.jdroid.javaweb.push;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import com.jdroid.java.exception.UnexpectedException;
import com.jdroid.javaweb.domain.Entity;

/**
 * 
 * @author Maxi Rosson
 */
@javax.persistence.Entity
public class Device extends Entity {
	
	private Long userId;
	private String installationId;
	private String registrationId;
	
	@Enumerated(value = EnumType.STRING)
	private DeviceType deviceType;
	
	/**
	 * Default constructor.
	 */
	@SuppressWarnings("unused")
	private Device() {
		// Do nothing, is required by hibernate
	}
	
	public Device(Long userId, String installationId, String registrationId, DeviceType deviceType) {
		
		if (deviceType == null) {
			throw new UnexpectedException("The device type is required");
		}
		
		this.userId = userId;
		this.installationId = installationId;
		this.registrationId = registrationId;
		this.deviceType = deviceType;
	}
	
	public void updateRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}
	
	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}
	
	/**
	 * @return the installationId
	 */
	public String getInstallationId() {
		return installationId;
	}
	
	/**
	 * @return the registrationId
	 */
	public String getRegistrationId() {
		return registrationId;
	}
	
	/**
	 * @return the deviceType
	 */
	public DeviceType getDeviceType() {
		return deviceType;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Device [userId=" + userId + ", installationId=" + installationId + ", registrationId=" + registrationId
				+ ", deviceType=" + deviceType + "]";
	}
	
}

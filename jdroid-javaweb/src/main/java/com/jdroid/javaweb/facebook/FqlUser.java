package com.jdroid.javaweb.facebook;

import com.restfb.Facebook;

public class FqlUser {
	
	@Facebook("uid")
	private Long id;
	
	@Facebook
	private String name;
	
	@Facebook("is_app_user")
	private Boolean isAppUser;
	
	public FqlUser() {
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the isAppUser
	 */
	public Boolean getIsAppUser() {
		return isAppUser;
	}
	
	/**
	 * @param isAppUser the isAppUser to set
	 */
	public void setIsAppUser(Boolean isAppUser) {
		this.isAppUser = isAppUser;
	}
	
}

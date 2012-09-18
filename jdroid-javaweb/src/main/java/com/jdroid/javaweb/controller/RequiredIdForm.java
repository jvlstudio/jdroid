package com.jdroid.javaweb.controller;

import javax.ws.rs.FormParam;

/**
 * 
 * @author Maxi Rosson
 */
public class RequiredIdForm extends AbstractForm {
	
	@FormParam("id")
	@Required
	private Long id;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
}
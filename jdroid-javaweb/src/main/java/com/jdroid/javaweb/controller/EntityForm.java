package com.jdroid.javaweb.controller;

import javax.ws.rs.FormParam;
import javax.ws.rs.QueryParam;
import org.jboss.resteasy.spi.BadRequestException;

public class EntityForm extends AbstractForm {
	
	@QueryParam(value = "id")
	private Long queryParamId;
	
	@FormParam(value = "id")
	private Long postId;
	
	/**
	 * @see com.jdroid.javaweb.controller.AbstractForm#doValidate()
	 */
	@Override
	protected void doValidate() {
		if (this.getId() == null) {
			throw new BadRequestException("Missing required field id");
		}
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return postId != null ? postId : queryParamId;
	}
	
}

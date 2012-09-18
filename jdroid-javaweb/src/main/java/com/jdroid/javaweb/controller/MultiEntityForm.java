package com.jdroid.javaweb.controller;

import java.util.Collection;
import java.util.List;
import javax.ws.rs.FormParam;
import javax.ws.rs.QueryParam;
import org.jboss.resteasy.spi.BadRequestException;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.jdroid.javaweb.guava.function.StringToLongFunction;
import com.jdroid.javaweb.utils.CSVUtils;

public class MultiEntityForm extends AbstractForm {
	
	@FormParam(value = "ids")
	private String postIds;
	
	@QueryParam(value = "ids")
	private String queryParamIds;
	
	private List<Long> parsedIds;
	
	/**
	 * @see com.jdroid.javaweb.controller.AbstractForm#doValidate()
	 */
	@Override
	protected void doValidate() {
		if (this.getFormIds() == null) {
			throw new BadRequestException("Missing required field ids");
		}
	}
	
	public Collection<Long> getIds() {
		
		if (parsedIds == null) {
			try {
				parsedIds = Lists.newArrayList(Iterables.transform(CSVUtils.fromCSV(getFormIds()),
					new StringToLongFunction()));
			} catch (NumberFormatException e) {
				throw new BadRequestException("The parameter 'ids' must be CSV list of long values");
			}
		}
		
		return parsedIds;
	}
	
	private String getFormIds() {
		return postIds != null ? postIds : queryParamIds;
	}
	
}

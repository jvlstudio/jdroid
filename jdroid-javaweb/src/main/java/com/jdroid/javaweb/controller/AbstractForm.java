package com.jdroid.javaweb.controller;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import org.jboss.resteasy.spi.BadRequestException;
import com.jdroid.java.exception.UnexpectedException;
import com.restfb.util.ReflectionUtils;
import com.restfb.util.ReflectionUtils.FieldWithAnnotation;

public abstract class AbstractForm {
	
	public final void validate() {
		checkRequired();
		doValidate();
	}
	
	protected void doValidate() {
		// Do Nothing
	}
	
	private void checkRequired() {
		
		for (FieldWithAnnotation<Required> fieldWithAnnotation : ReflectionUtils.findFieldsWithAnnotation(
			this.getClass(), Required.class)) {
			Field field = fieldWithAnnotation.getField();
			
			if ((!Modifier.isPublic(field.getModifiers())
					|| !Modifier.isPublic(field.getDeclaringClass().getModifiers()) || Modifier.isFinal(field.getModifiers()))
					&& !field.isAccessible()) {
				field.setAccessible(true);
			}
			
			try {
				if (field.get(this) == null) {
					throw new BadRequestException("Missing required field " + field.getName());
				}
			} catch (IllegalAccessException e) {
				throw new UnexpectedException(e);
			}
		}
	}
}

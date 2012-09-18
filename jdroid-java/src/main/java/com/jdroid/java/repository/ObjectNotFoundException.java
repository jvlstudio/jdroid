package com.jdroid.java.repository;

import com.jdroid.java.exception.AbstractException;

public class ObjectNotFoundException extends AbstractException {
	
	public ObjectNotFoundException(Class<?> entityClass, Long id) {
		super("The entity of type " + entityClass.getSimpleName() + " and id = " + id + " wasn't found.");
	}
	
	public ObjectNotFoundException(Class<?> entityClass) {
		super("The entity of type " + entityClass.getSimpleName() + " wasn't found.");
	}
	
	public ObjectNotFoundException(Long id) {
		super("The entity of id = " + id + " wasn't found.");
	}
}

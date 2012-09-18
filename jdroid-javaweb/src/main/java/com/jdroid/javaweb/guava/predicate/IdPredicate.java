package com.jdroid.javaweb.guava.predicate;

import com.jdroid.java.domain.Identifiable;


/**
 * Predicate that match an entity with the id
 * 
 * @param <E> The entity type
 */
public class IdPredicate<E extends Identifiable> extends EqualsPropertyPredicate<E> {
	
	/**
	 * @param entityId The entity id
	 */
	public IdPredicate(Long entityId) {
		super("id", entityId);
	}
	
}

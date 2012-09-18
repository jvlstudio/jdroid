package com.jdroid.javaweb.domain;

import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import com.jdroid.java.domain.Identifiable;

/**
 * Domain Entity
 */
@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class Entity implements Serializable, Identifiable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Since equality has been redefined, so must be the hashCode function.
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		return (id != null) ? prime * id.hashCode() : super.hashCode();
	}
	
	/**
	 * Redefines equality depending on the id of the entities being compared.
	 * 
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!getClass().isAssignableFrom(obj.getClass())) {
			return false;
		}
		Entity other = (Entity)obj;
		
		if (id != null) {
			return id.equals(other.getId());
		}
		
		if (other.getId() != null) {
			return false;
		}
		
		return super.equals(other);
	}
	
	/**
	 * Gets the identification for this {@link Entity}
	 * 
	 * @return the id of this {@link Entity}
	 */
	@Override
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
}

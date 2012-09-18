package com.jdroid.android.domain;

import com.jdroid.java.domain.Identifiable;

/**
 * Domain Entity
 * 
 * @author Maxi Rosson
 */
public class Entity extends BusinessObject implements Identifiable {
	
	private Long id;
	
	/**
	 * @param id
	 */
	public Entity(Long id) {
		this.id = id;
	}
	
	public Entity() {
	}
	
	/**
	 * @see com.jdroid.java.domain.Identifiable#getId()
	 */
	@Override
	public Long getId() {
		return id;
	}
	
	/**
	 * Since equality has been redefined, so must be the hashCode function.
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	/**
	 * Redefines equality depending on the id of the entities being compared.
	 * 
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Entity other = (Entity)obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
}

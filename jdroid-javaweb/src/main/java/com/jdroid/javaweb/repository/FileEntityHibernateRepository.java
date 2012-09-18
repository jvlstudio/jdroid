package com.jdroid.javaweb.repository;

import com.jdroid.java.repository.ObjectNotFoundException;
import com.jdroid.javaweb.domain.FileEntity;
import com.jdroid.javaweb.hibernate.AbstractHibernateRepository;

/**
 * 
 * @author Maxi Rosson
 */
public class FileEntityHibernateRepository extends AbstractHibernateRepository<FileEntity> implements
		FileEntityRepository {
	
	protected FileEntityHibernateRepository() {
		super(FileEntity.class);
	}
	
	/**
	 * @see com.jdroid.javaweb.repository.FileEntityRepository#getByName(java.lang.String)
	 */
	@Override
	public FileEntity getByName(String name) throws ObjectNotFoundException {
		return findUnique("name", name);
	}
}

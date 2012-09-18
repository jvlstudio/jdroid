package com.jdroid.javaweb.push;

import java.util.Collection;
import org.hibernate.criterion.Restrictions;
import com.jdroid.javaweb.hibernate.AbstractHibernateRepository;

public class DeviceHibernateRepository extends AbstractHibernateRepository<Device> implements DeviceRepository {
	
	protected DeviceHibernateRepository() {
		super(Device.class);
	}
	
	/**
	 * @see com.jdroid.javaweb.push.DeviceRepository#find(java.lang.Long, java.lang.String)
	 */
	@Override
	public Device find(Long userId, String installationId) {
		return this.findUnique(this.createDetachedCriteria().add(Restrictions.eq("userId", userId)).add(
			Restrictions.eq("installationId", installationId)));
	}
	
	/**
	 * @see com.jdroid.javaweb.push.DeviceRepository#findByUserId(java.lang.Long)
	 */
	@Override
	public Collection<Device> findByUserId(Long userId) {
		return find("userId", userId);
	}
	
}

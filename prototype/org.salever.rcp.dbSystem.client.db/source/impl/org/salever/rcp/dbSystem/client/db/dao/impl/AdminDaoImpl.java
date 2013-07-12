package org.salever.rcp.dbSystem.client.db.dao.impl;

// Generated 2010-10-30 13:33:23 by Hibernate Tools 3.2.4.CR1

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.salever.rcp.dbSystem.client.db.dao.AdminDao;
import org.salever.rcp.dbSystem.client.db.dao.common.BaseHibernateDao;
import org.salever.rcp.dbSystem.client.db.model.Admin;

/**
 * DaoImpl object for domain model class Admin.
 * 
 * @see
 * @author Hibernate Tools
 */
public class AdminDaoImpl extends BaseHibernateDao<Admin, Integer> implements
		AdminDao {

	private static final Log log = LogFactory.getLog(AdminDaoImpl.class);

	@Override
	public Admin findByUserAndPwd(String username, String pwd) {
		List<Admin> result = super.findByCriteria(Order.desc("id"),
				Restrictions.eq("username", username),
				Restrictions.eq("password", pwd));
		if (result == null || result.isEmpty()) {
			return null;
		}
		return result.get(0);
	}

}

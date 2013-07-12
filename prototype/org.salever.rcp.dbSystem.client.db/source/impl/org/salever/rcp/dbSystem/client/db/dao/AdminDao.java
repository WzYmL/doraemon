package org.salever.rcp.dbSystem.client.db.dao;

import org.salever.rcp.dbSystem.client.db.dao.common.GenericDao;
import org.salever.rcp.dbSystem.client.db.model.Admin;

public interface AdminDao extends GenericDao<Admin, Integer> {

	Admin findByUserAndPwd(String username, String string);

}

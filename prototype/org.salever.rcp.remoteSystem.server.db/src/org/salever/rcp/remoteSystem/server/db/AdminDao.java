package org.salever.rcp.remoteSystem.server.db;

import org.salever.rcp.remoteSystem.server.db.common.GenericDao;
import org.salever.rcp.remoteSystem.server.model.Admin;


public interface AdminDao extends GenericDao<Admin, Integer> {

	Admin findByUserAndPwd(String username, String string);

	Admin findByUser(String username);

	/**
	 * @param realName
	 * @return
	 */
	Admin findByRealName(String realName);
}

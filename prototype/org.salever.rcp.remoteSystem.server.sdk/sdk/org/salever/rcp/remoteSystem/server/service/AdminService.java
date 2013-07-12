/**
 * 
 */
package org.salever.rcp.remoteSystem.server.service;

import java.util.List;

import org.salever.rcp.remoteSystem.server.model.Admin;


/**
 * @author dev
 * 
 */
public interface AdminService {

	Admin findByUsername(String username);

	Admin findByUsernameAndPwd(String username, String pwd);

	Admin save(Admin model);

	/**
	 * @return
	 */
	List<Admin> findAll();

	/**
	 * @param realName
	 * @return
	 */
	Admin findByRealName(String realName);

	Admin findById(int id);

	public void delete(Admin model);

}

/**
 * 
 */
package org.salever.rcp.remoteSystem.server.service.impl;

import java.util.List;

import org.salever.rcp.remoteSystem.server.db.AdminDao;
import org.salever.rcp.remoteSystem.server.model.Admin;
import org.salever.rcp.remoteSystem.server.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author dev
 * 
 */
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDao adminDao;

	@Override
	public Admin findByUsername(String username) {
		return adminDao.findByUser(username);
	}

	@Override
	public Admin findByUsernameAndPwd(String username, String pwd) {
		return adminDao.findByUserAndPwd(username, pwd);
	}

	@Override
	public Admin save(Admin model) {
		return adminDao.makePersistent(model);
	}

	@Override
	public List<Admin> findAll() {
		return adminDao.findAll();
	}

	@Override
	public Admin findByRealName(String realName) {
		return adminDao.findByRealName(realName);
	}

	@Override
	public Admin findById(int id) {
		return adminDao.findById(id);
	}

	@Override
	public void delete(Admin model) {
		adminDao.makeTransient(model);
	}

}

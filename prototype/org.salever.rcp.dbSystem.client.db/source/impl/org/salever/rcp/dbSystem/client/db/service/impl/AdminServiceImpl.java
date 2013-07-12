/*******************************************************************************
 * Copyright (c) 2010 liXiaopeng. All rights reserved. 
 *
 *
 * Contributors:
 *     LiXiaopeng - initial API and implementation 
 *     Email:salever@126.com
 *
 * Create on 2011-10-5 下午01:25:40
 *******************************************************************************/
package org.salever.rcp.dbSystem.client.db.service.impl;

import java.util.List;

import org.salever.rcp.dbSystem.client.db.dao.AdminDao;
import org.salever.rcp.dbSystem.client.db.model.Admin;
import org.salever.rcp.dbSystem.client.db.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by @author LiXiaopeng
 * 
 * Comments.
 * 
 */
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDao adminDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.readidtech.data.client.db.service.AdminService#delete(com.readidtech
	 * .data.client.db.model.Admin)
	 */
	@Override
	public void delete(Admin model) {
		adminDao.makeTransient(model);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.readidtech.data.client.db.service.AdminService#findAll()
	 */
	@Override
	public List<Admin> findAll() {
		return adminDao.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.readidtech.data.client.db.service.AdminService#findById(int)
	 */
	@Override
	public Admin findById(int id) {
		return adminDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.readidtech.data.client.db.service.AdminService#insert(com.readidtech
	 * .data.client.db.model.Admin)
	 */
	@Override
	public void insert(Admin model) {
		save(model);
	}

	@Override
	public Admin login(String username, String password) {
		if (password == null || username == null) {
			return null;
		}
		// String md5Hex = org.apache.commons.codec.digest.DigestUtils
		// .md5Hex(password);
		String md5Hex = password;
		Admin model = adminDao.findByUserAndPwd(username, md5Hex);
		return model;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.readidtech.data.client.db.service.AdminService#save(com.readidtech
	 * .data.client.db.model.Admin)
	 */
	@Override
	public void save(Admin model) {
		String password = model.getPassword();
		String md5Hex = org.apache.commons.codec.digest.DigestUtils
				.md5Hex(password);
		model.setPassword(md5Hex);
		adminDao.makePersistent(model);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.readidtech.data.client.db.service.AdminService#saveThenDeleteOthers
	 * (java.util.List)
	 */
	@Override
	public void saveThenDeleteOthers(List<Admin> listToKeep) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.readidtech.data.client.db.service.AdminService#update(com.readidtech
	 * .data.client.db.model.Admin)
	 */
	@Override
	public void update(Admin model) {
		save(model);
	}

}

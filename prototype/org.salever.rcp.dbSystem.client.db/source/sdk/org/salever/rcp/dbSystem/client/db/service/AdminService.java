package org.salever.rcp.dbSystem.client.db.service;

import java.util.List;

import org.salever.rcp.dbSystem.client.db.model.Admin;

/**
 * 
 */
public interface AdminService {

	public void delete(Admin model);

	public List<Admin> findAll();

	public Admin findById(int id);

	void insert(Admin model);

	public Admin login(String username, String password);

	void save(Admin model);

	public void saveThenDeleteOthers(List<Admin> listToKeep);

	void update(Admin model);
}

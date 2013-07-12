package org.salever.rcp.dbSystem.client.db.dao.common;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, ID extends Serializable> {
	List<T> findAll();

	List<T> findByExample(T exampleInstance);

	List<T> findByExample(T exampleInstance, String[] excludeProperty);

	public T findById(ID id);

	T findById(ID id, boolean lock);

	void flush();

	T makePersistent(T entity);

	void makeTransient(T entity);

}

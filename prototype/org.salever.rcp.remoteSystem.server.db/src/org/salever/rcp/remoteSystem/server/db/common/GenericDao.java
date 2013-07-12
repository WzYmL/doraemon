package org.salever.rcp.remoteSystem.server.db.common;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, ID extends Serializable> {
	public T findById(ID id);

	T findById(ID id, boolean lock);

	List<T> findAll();

	List<T> findByExample(T exampleInstance);

	List<T> findByExample(T exampleInstance, String[] excludeProperty);

	T makePersistent(T entity);

	void makeTransient(T entity);

	void flush();

	/**
	 * 清空数据库
	 */
	void clean();

}

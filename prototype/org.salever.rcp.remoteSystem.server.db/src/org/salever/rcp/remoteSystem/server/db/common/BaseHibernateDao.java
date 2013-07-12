package org.salever.rcp.remoteSystem.server.db.common;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.StatementCreatorUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BaseHibernateDao<T, ID extends Serializable> extends
		HibernateDaoSupport implements GenericDao<T, ID> {

	private Log log = LogFactory.getLog(BaseHibernateDao.class);

	private JdbcTemplate jdbcTemplate;

	/**
	 * @param jdbcTemplate
	 *            the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public BaseHibernateDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	@SuppressWarnings("unchecked")
	public T findById(ID id) {
		if (id == null) {
			return null;
		}
		return (T) getHibernateTemplate().get(getPersistentClass(), id);
	}

	@SuppressWarnings("unchecked")
	public T findById(ID id, Class clazz) {
		if (id == null || clazz == null) {
			return null;
		}
		return (T) getHibernateTemplate().get(clazz, id);
	}

	@SuppressWarnings("unchecked")
	public T findById(ID id, boolean lock) {
		if (id == null) {
			return null;
		}
		T entity;
		if (lock) {
			entity = (T) getHibernateTemplate().load(getPersistentClass(), id,
					LockMode.UPGRADE);
		} else {
			entity = (T) getHibernateTemplate().load(getPersistentClass(), id);
		}

		return entity;
	}

	@SuppressWarnings("unchecked")
	public T findById(ID id, boolean lock, Class clazz) {
		if (id == null || clazz == null) {
			return null;
		}
		T entity;
		if (lock) {
			entity = (T) getHibernateTemplate().load(clazz, id,
					LockMode.UPGRADE);
		} else {
			entity = (T) getHibernateTemplate().load(clazz, id);
		}

		return entity;
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return findByCriteria(null);
	}

	@SuppressWarnings("unchecked")
	public List<T> findByExample(final T exampleInstance) {
		if (exampleInstance == null) {
			return new ArrayList<T>();
		}
		return findByExample(exampleInstance, new String[0]);
	}

	@SuppressWarnings("unchecked")
	protected List<T> findByExample(final T exampleInstance, Class clazz) {
		if (exampleInstance == null || clazz == null) {
			return new ArrayList<T>();
		}
		return findByExample(exampleInstance, new String[0], clazz);
	}

	@SuppressWarnings("unchecked")
	public List<T> findByExample(final T exampleInstance,
			final String[] excludeProperty) {
		if (exampleInstance == null) {
			return new ArrayList<T>();
		}
		List<T> list = (List<T>) getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Criteria crit = session
								.createCriteria(getPersistentClass());
						Example example = Example.create(exampleInstance);
						for (String exclude : excludeProperty) {
							example.excludeProperty(exclude);
						}
						crit.add(example);
						return crit.list();
					}
				});
		return list;
	}

	@SuppressWarnings("unchecked")
	protected List<T> findByExample(final T exampleInstance,
			final String[] excludeProperty, final Class clazz) {
		if (exampleInstance == null || clazz == null) {
			return new ArrayList<T>();
		}
		List<T> list = (List<T>) getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Criteria crit = session.createCriteria(clazz);
						Example example = Example.create(exampleInstance);
						for (String exclude : excludeProperty) {
							example.excludeProperty(exclude);
						}
						crit.add(example);
						return crit.list();
					}
				});
		return list;
	}

	@SuppressWarnings("unchecked")
	public T makePersistent(T entity) {
		if (entity == null) {
			return null;
		}
		getHibernateTemplate().saveOrUpdate(entity);
		return entity;
	}

	public Object makePersistent(String entityName, Object entity) {
		if (entity == null) {
			return null;
		}
		if (entityName == null) {
			getHibernateTemplate().saveOrUpdate(entity);
		} else {
			getHibernateTemplate().saveOrUpdate(entityName, entity);
		}
		return entity;
	}

	public void makeTransient(T entity) {
		if (entity == null) {
			return;
		}
		getHibernateTemplate().delete(entity);
	}

	public void makeTransient(String entityName, Object entity) {
		if (entity == null) {
			return;
		}
		if (entityName == null) {
			getHibernateTemplate().delete(entity);
		} else {
			getHibernateTemplate().delete(entityName, entity);
		}
	}

	/**
	 * Use this inside subclasses as a convenience method.
	 */
	protected List<T> findByCriteria(final Order order,
			final Criterion... criterion) {
		return findByCriteria(-1, -1, order, criterion);
	}

	protected List<T> findByDetachedCriteria(final DetachedCriteria criteria) {
		if (criteria == null) {
			// 发现重载findByCriteria带来了findByCriteria(null)匹配到该函数，
			// 而不是原有的函数，因此取消重载，使用新的方法名。
			// 为了保险，对null参数进行判断
			findByCriteria(-1, -1, null);
		}
		return findByDetachedCriteria(-1, -1, criteria);
	}

	@SuppressWarnings("unchecked")
	protected List<T> findByDetachedCriteria(final int curPage,
			final int pageSize, final DetachedCriteria criteria) {
		if (criteria == null) {
			// 发现重载findByCriteria带来了findByCriteria(null)匹配到该函数，
			// 而不是原有的函数，因此取消重载，使用新的方法名。
			// 为了保险，对null参数进行判断
			findByCriteria(curPage, pageSize, null);
		}
		if (curPage >= 0 && pageSize >= 0) {
			return getHibernateTemplate().findByCriteria(criteria,
					curPage * pageSize, pageSize);
		} else {
			return getHibernateTemplate().findByCriteria(criteria);
		}
	}

	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(final int curPage, final int pageSize,
			final Order order, final Criterion... criterion) {
		List<T> list = (List<T>) getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Criteria crit = session
								.createCriteria(getPersistentClass());
						for (Criterion c : criterion) {
							crit.add(c);
						}

						if (curPage >= 0 && pageSize >= 0) {
							crit.setFirstResult(curPage * pageSize);
							crit.setMaxResults(pageSize);
						}
						if (order != null) {
							crit.addOrder(order);
						}
						return crit.list();
					}
				});
		return list;
	}

	protected Integer execute(final String hql, final Object... parameters) {
		if (hql == null) {
			return null;
		}
		Integer result = (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						for (int i = 0; i < parameters.length; i++) {
							query.setParameter(i, parameters[i]);
						}
						Integer result = query.executeUpdate();
						return result;
					}

				});
		return result;
	}

	@SuppressWarnings("unchecked")
	protected <E> List<E> findByHql(Class<E> resultClass, final int curPage,
			final int pageSize, final String hql, final Object... parameters) {
		if (hql == null) {
			return null;
		}
		List<E> list = (List<E>) getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						for (int i = 0; i < parameters.length; i++) {
							query.setParameter(i, parameters[i]);
						}
						if (curPage >= 0 && pageSize >= 0) {
							query.setFirstResult(curPage * pageSize);
							query.setMaxResults(pageSize);
						}
						List list = query.list();
						return list;
					}
				});
		return list;
	}

	@SuppressWarnings("unchecked")
	protected <E> List<E> findByHqlLimit(Class<E> resultClass,
			final int startIndex, final int pageSize, final String hql,
			final Object... parameters) {
		if (hql == null) {
			return null;
		}
		List<E> list = (List<E>) getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						for (int i = 0; i < parameters.length; i++) {
							query.setParameter(i, parameters[i]);
						}
						if (startIndex >= 0 && pageSize >= 0) {
							query.setFirstResult(startIndex);
							query.setMaxResults(pageSize);
						}
						List list = query.list();
						return list;
					}
				});
		return list;
	}

	@SuppressWarnings("unchecked")
	protected <E> List<E> findByHql(Class<E> resultClass, String hql,
			Object... parameters) {
		if (hql == null) {
			return null;
		}
		return getHibernateTemplate().find(hql, parameters);
	}

	@SuppressWarnings("unchecked")
	protected Long countByCondition(String hql, Object... parameters) {
		if (hql == null) {
			return null;
		}
		List list = getHibernateTemplate().find(hql, parameters);
		Long result = Long.valueOf(list.get(0).toString());
		return result;

	}

	@SuppressWarnings("unchecked")
	protected <E> List<E> findBySql(RowMapper<E> mappper, String sql,
			Object[] parameters, int[] argTypes) {
		if (sql == null) {
			return null;
		}
		return jdbcTemplate.query(sql, parameters, argTypes, mappper);
	}

	@SuppressWarnings("unchecked")
	protected List<Map<String, Object>> findBySql(String sql,
			Object[] parameters, int[] argTypes) {
		if (sql == null) {
			return null;
		}
		return jdbcTemplate.queryForList(sql, parameters, argTypes);
	}

	@SuppressWarnings("unchecked")
	protected <E> List<E> findBySql(RowMapper<E> mappper, String sql,
			Object[] parameters, int[] argTypes, int curPage, int pageSize) {
		if (sql == null) {
			return null;
		}
		if (curPage < 0 || pageSize < 0) {
			return findBySql(mappper, sql, parameters, argTypes);
		}

		// oracle实现
		// long firstRowNum = (long) curPage * (long) pageSize + 1;
		// long endRowNum = firstRowNum + (long) pageSize;
		// sql = "select * from (select v.*, ROWNUM rn from (" + sql +
		// ") v where rownum < " + endRowNum + ") where rn >= " + firstRowNum;

		// mysql实现
		long lastRowNum = ((long) curPage) * pageSize;
		long size = pageSize;
		sql = sql + " LIMIT " + lastRowNum + ", " + size;

		return jdbcTemplate.query(sql, parameters, argTypes, mappper);
	}

	protected int executeBySql(final String sql, final Object... parameters) {
		if (sql == null) {
			return 0;
		}
		return jdbcTemplate.update(sql, parameters);
	}

	protected int executeBySql(final String sql, final Object[] parameters,
			final int[] argTypes) {
		if (sql == null) {
			return 0;
		}
		return jdbcTemplate.update(sql, parameters, argTypes);
	}

	/**
	 * 插入一条记录，并获得生成的主键 add by zengyunfeng
	 * 
	 * @version 1.2.0
	 * @param sql
	 * @param parameters
	 * @param argTypes
	 * @return
	 */
	protected Number insertBySql(final String sql, final Object[] parameters,
			final int[] argTypes) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				PreparedStatement ps = conn.prepareStatement(sql);
				setValues(ps, parameters, argTypes);
				return ps;
			}

			private void setValues(PreparedStatement ps, final Object[] args,
					final int[] argTypes) throws SQLException {
				int argIndx = 1;
				if (args != null) {
					for (int i = 0; i < args.length; i++) {
						Object arg = args[i];
						if (arg instanceof Collection
								&& argTypes[i] != Types.ARRAY) {
							Collection entries = (Collection) arg;
							for (Iterator it = entries.iterator(); it.hasNext();) {
								Object entry = it.next();
								if (entry instanceof Object[]) {
									Object[] valueArray = ((Object[]) entry);
									for (int k = 0; k < valueArray.length; k++) {
										Object argValue = valueArray[k];
										StatementCreatorUtils
												.setParameterValue(ps,
														argIndx++, argTypes[i],
														argValue);
									}
								} else {
									StatementCreatorUtils.setParameterValue(ps,
											argIndx++, argTypes[i], entry);
								}
							}
						} else {
							StatementCreatorUtils.setParameterValue(ps,
									argIndx++, argTypes[i], arg);
						}
					}
				}
			}
		}, keyHolder);
		return keyHolder.getKey();
	}

	@SuppressWarnings("unchecked")
	protected Long countBySql(String sql, Object[] parameters, int[] argTypes) {
		if (sql == null) {
			return null;
		}
		BigDecimal length = BigDecimal.valueOf(0);
		List list = jdbcTemplate.queryForList(sql, parameters, argTypes);
		if (list != null && list.size() > 0) {
			Map map = (Map) list.get(0);
			Iterator it = map.keySet().iterator();
			Object next = it.next();
			Object value = map.get(next);
			if (value instanceof BigDecimal) {
				length = (BigDecimal) value;
			} else if (value instanceof Long) {
				return (Long) value;
			}
		}
		return length.longValue();
	}

	public void flush() {
		getHibernateTemplate().flush();
	}

	/**
	 * @return the jdbcTemplate
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Override
	public void clean() {
		List<T> all = findAll();
		for (T t : all) {
			makeTransient(t);
		}
	}

}
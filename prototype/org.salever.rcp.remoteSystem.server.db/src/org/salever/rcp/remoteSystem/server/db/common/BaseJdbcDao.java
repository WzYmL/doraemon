package org.salever.rcp.remoteSystem.server.db.common;

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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.StatementCreatorUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/*
 * 使用JDBCTEMPLATE的基类
 * @author jaq
 * @version 1.0.0
 */

public class BaseJdbcDao {

	private Log log = LogFactory.getLog(this.getClass());
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
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
		// long firstRowNum = curPage * pageSize + 1;
		// long endRowNum = firstRowNum + pageSize;
		// sql = "select * from (select rownum rn,t.* from (" + sql
		// + ") t) where rn>=" + firstRowNum + " and rn<" + endRowNum;

		// mysql实现
		long lastRowNum = ((long) curPage) * pageSize;
		long size = pageSize;
		sql = sql + " LIMIT " + lastRowNum + ", " + size;

		return jdbcTemplate.query(sql, parameters, argTypes, mappper);
	}

	protected int executeBySql(final String sql, final Object[] parameters) {
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
	 * 插入一条记录，并获得生成的主键 2009-7-6 add by zengyunfeng
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
		Long length = Long.valueOf(0);
		List list = jdbcTemplate.queryForList(sql, parameters, argTypes);
		if (list != null && list.size() > 0) {
			Map map = (Map) list.get(0);
			Iterator it = map.keySet().iterator();
			length = (Long) map.get(it.next());
		}
		return length;
	}

	/**
	 * 获得满足条件的所有记录个数
	 * 
	 * @param sql
	 *            select sql语句
	 * @param parameters
	 *            参数
	 * @return 由数据MAP组成的LIST
	 */
	public List findByCondition(final String sql, final Object[] parameters) {
		return jdbcTemplate.queryForList(sql, parameters);
	}

	/**
	 * 获得满足条件的记录个数
	 * 
	 * @param sql
	 *            count sql语句
	 * @param parameters
	 * @return 满足条件的记录个数
	 */
	public Long countByCondition(final String sql, final Object[] parameters) {
		return jdbcTemplate.queryForLong(sql, parameters);
	}

	/**
	 * 分页获得返回结果
	 * 
	 * @param sql
	 *            执行select sql语句
	 * @param parameters
	 *            参数
	 * @param currPage
	 *            当前页码
	 * @param pageSize
	 *            每页个数
	 * @return 有数据MAP组成的LIST
	 */
	public List findByCondition(final String sql, final Object[] parameters,
			final Integer currPage, final Integer pageSize) {

		if (currPage == null || pageSize == null) {
			return new ArrayList(0);
		}

		String limitSql = sql;

		// oracle
		// long firstRowNum = currPage * pageSize + 1;
		// long endRowNum = firstRowNum + pageSize;
		// limitSql = "select * from (select rownum rn,t.* from (" + limitSql
		// + ") t) where rn>=" + firstRowNum + " and rn<" + endRowNum;

		// mysql
		int lastRowNum = currPage.intValue() * pageSize.intValue();
		int size = pageSize.intValue();
		limitSql = limitSql + " LIMIT " + lastRowNum + ", " + size;

		log.debug(limitSql);

		return jdbcTemplate.queryForList(limitSql, parameters);
	}

	/**
	 * 执行DDL操作
	 * 
	 * @param sql
	 *            DDL sql语句
	 * @param parameters
	 *            输入参数
	 * @return 更新的记录条数
	 */
	public int updateByCondition(String sql, Object[] parameters) {
		return jdbcTemplate.update(sql, parameters);
	}

	public void excuteSql(String sql) throws Exception {
		try {
			jdbcTemplate.execute(sql);
			log.info("sql execution success");
		} catch (Exception e) {
			log.error("sql execution fail");
			throw e;
		}
	}

	public int queryForInt(String sql, Object[] parameters) {
		return jdbcTemplate.queryForInt(sql, parameters);
	}

	public void truncateTable(String table) {
		String sql = "TRUNCATE table " + table;
		jdbcTemplate.execute(sql);
	}
}

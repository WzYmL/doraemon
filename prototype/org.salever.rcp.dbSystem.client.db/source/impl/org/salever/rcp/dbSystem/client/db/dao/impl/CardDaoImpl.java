package org.salever.rcp.dbSystem.client.db.dao.impl;

// Generated 2010-10-30 13:33:23 by Hibernate Tools 3.2.4.CR1

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.salever.rcp.dbSystem.client.db.criterion.CardCriterion;
import org.salever.rcp.dbSystem.client.db.dao.CardDao;
import org.salever.rcp.dbSystem.client.db.dao.common.BaseHibernateDao;
import org.salever.rcp.dbSystem.client.db.dao.common.RowMapper;
import org.salever.rcp.dbSystem.client.db.model.Card;

/**
 * DaoImpl object for domain model class Admin.
 * 
 * @see
 * @author Hibernate Tools
 */
public class CardDaoImpl extends BaseHibernateDao<Card, Integer> implements
		CardDao {

	private static final Log log = LogFactory.getLog(CardDaoImpl.class);

	private Object buildWhereClause(CardCriterion criterion,
			List<Object> params, List<Integer> types) {
		StringBuilder buffer = new StringBuilder();

		List<String> conditions = new ArrayList<String>();

		if (criterion.getCode() != null) {
			conditions.add("c.CODE = ?");
			params.add(criterion.getCode());
			types.add(Types.VARCHAR);
		}
		if (criterion.getStatus() != null) {
			conditions.add("c.STATUS = ?");
			params.add(criterion.getStatus());
			types.add(Types.INTEGER);
		}
		if (criterion.getType() != null) {
			conditions.add("c.DEVICE_TYPE = ?");
			params.add(criterion.getType());
			types.add(Types.INTEGER);
		}
		if (criterion.getFromWidth() != null) {
			conditions.add("c.CODE_WIDTH >= ?");
			params.add(criterion.getFromWidth());
			types.add(Types.INTEGER);
		}

		if (criterion.getToWidth() != null) {
			conditions.add("c.CODE_WIDTH <= ?");
			params.add(criterion.getToWidth());
			types.add(Types.INTEGER);
		}

		if (conditions.size() > 0) {
			buffer.append(" where ").append(
					StringUtils.join(conditions, " and "));
		}

		return buffer.toString();
	}

	@Override
	public long countByCriterion(CardCriterion criterion) {
		StringBuilder buffer = new StringBuilder();
		buffer.append("select count(c.id) from card c ");

		List<Object> params = new ArrayList<Object>();
		List<Integer> types = new ArrayList<Integer>();

		buffer.append(this.buildWhereClause(criterion, params, types));

		String sql = buffer.toString();

		log.info(sql);

		return super.countBySql(sql, params.toArray(),
				ArrayUtils.toPrimitive(types.toArray(new Integer[0])));
	}

	@Override
	public List<Card> listByCriterion(CardCriterion criterion) {

		int pageSize = criterion.getPageSize();
		int currentPage = criterion.getCurrentPage();

		currentPage--;

		StringBuilder buffer = new StringBuilder();
		buffer.append("select c.id from card c ");

		List<Object> params = new ArrayList<Object>();
		List<Integer> types = new ArrayList<Integer>();

		buffer.append(this.buildWhereClause(criterion, params, types));

		String sql = buffer.toString();

		log.info(sql);

		RowMapper<Integer> mapper = new RowMapper<Integer>() {

			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt(1);
			}
		};

		List<Integer> idList = super.findBySql(mapper, sql, params.toArray(),
				ArrayUtils.toPrimitive(types.toArray(new Integer[0])),
				currentPage, pageSize);

		Order order = Order.desc("id");

		if (idList == null || idList.size() == 0) {
			return new ArrayList<Card>();
		} else {
			List<Card> surveyList = super.findByCriteria(order,
					Restrictions.in("id", idList));

			return surveyList;
		}
	}
}

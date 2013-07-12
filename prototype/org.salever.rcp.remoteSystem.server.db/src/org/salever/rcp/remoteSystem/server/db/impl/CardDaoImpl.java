package org.salever.rcp.remoteSystem.server.db.impl;

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
import org.salever.rcp.remoteSystem.server.db.CardDao;
import org.salever.rcp.remoteSystem.server.db.common.BaseHibernateDao;
import org.salever.rcp.remoteSystem.server.db.common.RowMapper;
import org.salever.rcp.remoteSystem.server.model.Card;
import org.salever.rcp.remoteSystem.server.model.ceriterion.CardCriterion;


/**
 * DaoImpl object for domain model class Admin.
 * 
 * @see
 * @author Hibernate Tools
 */
public class CardDaoImpl extends BaseHibernateDao<Card, Integer> implements
		CardDao {

	private static final Log log = LogFactory.getLog(CardDaoImpl.class);

	@Override
	public Card findByCode(String cardId) {
		List<Card> result = super.findByCriteria(Order.desc("id"),
				Restrictions.eq("uniqueCode", cardId));
		if (result == null || result.isEmpty()) {
			return null;
		}
		return result.get(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.readidtech.kgarten.server.db.CardDao#listByCriterion(com.readidtech
	 * .kgarten.server.model.ceriterion.CardCriterion)
	 */
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
		buffer.append(" order by c.id DESC");
		String sql = buffer.toString();

		log.info(sql);

		RowMapper<Integer> mapper = new RowMapper<Integer>() {

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
			List<Card> list = super.findByCriteria(order,
					Restrictions.in("id", idList));

			return list;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.readidtech.kgarten.server.db.CardDao#countByCriterion(com.readidtech
	 * .kgarten.server.model.ceriterion.CardCriterion)
	 */
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

	private Object buildWhereClause(CardCriterion criterion,
			List<Object> params, List<Integer> types) {
		StringBuilder buffer = new StringBuilder();

		List<String> conditions = new ArrayList<String>();

		if (criterion.getCode() != null) {
			conditions.add("c.UNIQUE_CODE = ?");
			params.add(criterion.getCode());
			types.add(Types.VARCHAR);
		}

		if (criterion.getStatuses() != null) {
			String join = StringUtils.join(criterion.getStatuses(), " , ");
			conditions.add("c.STATUS in ( " + join + " )");
		}
		if (criterion.getTypes() != null) {
			String join = StringUtils.join(criterion.getTypes(), " , ");
			conditions.add("c.TYPE in ( " + join + " )");
		}

		if (conditions.size() > 0) {
			buffer.append(" where ").append(
					StringUtils.join(conditions, " and "));
		}

		return buffer.toString();
	}

}

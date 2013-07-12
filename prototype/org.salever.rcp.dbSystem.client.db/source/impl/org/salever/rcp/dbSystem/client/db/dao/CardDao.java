package org.salever.rcp.dbSystem.client.db.dao;

import java.util.List;

import org.salever.rcp.dbSystem.client.db.criterion.CardCriterion;
import org.salever.rcp.dbSystem.client.db.dao.common.GenericDao;
import org.salever.rcp.dbSystem.client.db.model.Card;

public interface CardDao extends GenericDao<Card, Integer> {

	long countByCriterion(CardCriterion criterion);

	List<Card> listByCriterion(CardCriterion criterion);

}

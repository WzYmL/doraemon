package org.salever.rcp.remoteSystem.server.db;

import java.util.List;

import org.salever.rcp.remoteSystem.server.db.common.GenericDao;
import org.salever.rcp.remoteSystem.server.model.Card;
import org.salever.rcp.remoteSystem.server.model.ceriterion.CardCriterion;


public interface CardDao extends GenericDao<Card, Integer> {

	Card findByCode(String cardId);

	/**
	 * @param criterion
	 * @return
	 */
	List<Card> listByCriterion(CardCriterion criterion);

	/**
	 * @param criterion
	 * @return
	 */
	long countByCriterion(CardCriterion criterion);

}

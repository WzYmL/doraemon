package org.salever.rcp.dbSystem.client.db.service;

import java.util.List;

import org.salever.rcp.dbSystem.client.db.criterion.CardCriterion;
import org.salever.rcp.dbSystem.client.db.model.Card;

/**
 * 
 */
public interface CardService {

	public long countByCriterion(CardCriterion criterion);

	public void delete(Card model);

	public List<Card> findAll();

	public Card findById(int id);

	void insert(Card model);

	public List<Card> listByCriterion(CardCriterion criterion);

	void save(Card model);

	public void saveThenDeleteOthers(List<Card> listToKeep);

	void update(Card model);
}

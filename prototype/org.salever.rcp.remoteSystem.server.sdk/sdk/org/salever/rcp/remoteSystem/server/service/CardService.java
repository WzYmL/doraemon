package org.salever.rcp.remoteSystem.server.service;

import java.util.List;

import org.salever.rcp.remoteSystem.server.model.Card;
import org.salever.rcp.remoteSystem.server.model.ceriterion.CardCriterion;


/**
 * 
 */
public interface CardService {

	public Card findById(int id);

	void insert(Card model);

	void update(Card model);

	Card save(Card model);

	public List<Card> findAll();

	public void delete(Card model);

	public void saveThenDeleteOthers(List<Card> listToKeep);

	public Card findByCode(String uniqueCode);

	public List<Card> listByCriterion(CardCriterion criterion);

	public long countByCriterion(CardCriterion criterion);

	public void clean();
}

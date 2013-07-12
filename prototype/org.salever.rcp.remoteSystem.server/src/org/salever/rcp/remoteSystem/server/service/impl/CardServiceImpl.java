/*******************************************************************************
 * Copyright (c) 2010 liXiaopeng. All rights reserved. 
 *
 *
 * Contributors:
 *     LiXiaopeng - initial API and implementation 
 *     Email:salever@126.com
 *
 * Create on 2011-10-5 下午01:25:40
 *******************************************************************************/
package org.salever.rcp.remoteSystem.server.service.impl;

import java.util.List;

import org.salever.rcp.remoteSystem.server.db.CardDao;
import org.salever.rcp.remoteSystem.server.model.Card;
import org.salever.rcp.remoteSystem.server.model.ceriterion.CardCriterion;
import org.salever.rcp.remoteSystem.server.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by @author LiXiaopeng
 * 
 * Comments.
 * 
 */
public class CardServiceImpl implements CardService {

	@Autowired
	CardDao cardDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.readidtech.data.client.db.service.CardService#findById(int)
	 */
	public Card findById(int id) {
		return cardDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.readidtech.data.client.db.service.CardService#insert(com.readidtech
	 * .data.client.db.model.Card)
	 */
	public void insert(Card model) {
		cardDao.makePersistent(model);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.readidtech.data.client.db.service.CardService#update(com.readidtech
	 * .data.client.db.model.Card)
	 */
	public void update(Card model) {
		cardDao.makePersistent(model);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.readidtech.data.client.db.service.CardService#save(com.readidtech
	 * .data.client.db.model.Card)
	 */
	public Card save(Card model) {
		return cardDao.makePersistent(model);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.readidtech.data.client.db.service.CardService#findAll()
	 */
	public List<Card> findAll() {
		return cardDao.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.readidtech.data.client.db.service.CardService#delete(com.readidtech
	 * .data.client.db.model.Card)
	 */
	public void delete(Card model) {
		cardDao.makeTransient(model);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.readidtech.data.client.db.service.CardService#saveThenDeleteOthers
	 * (java.util.List)
	 */
	public void saveThenDeleteOthers(List<Card> listToKeep) {

	}

	@Override
	public Card findByCode(String uniqueCode) {
		return cardDao.findByCode(uniqueCode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.readidtech.kgarten.server.service.CardService#listByCriterion(com
	 * .readidtech.kgarten.server.model.ceriterion.CardCriterion)
	 */
	@Override
	public List<Card> listByCriterion(CardCriterion criterion) {
		return cardDao.listByCriterion(criterion);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.readidtech.kgarten.server.service.CardService#countByCriterion(com
	 * .readidtech.kgarten.server.model.ceriterion.CardCriterion)
	 */
	@Override
	public long countByCriterion(CardCriterion criterion) {
		return cardDao.countByCriterion(criterion);
	}

	@Override
	public void clean() {
		cardDao.clean();
	}
}

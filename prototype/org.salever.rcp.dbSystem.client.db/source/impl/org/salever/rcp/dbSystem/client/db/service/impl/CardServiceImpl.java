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
package org.salever.rcp.dbSystem.client.db.service.impl;

import java.util.List;

import org.salever.rcp.dbSystem.client.db.criterion.CardCriterion;
import org.salever.rcp.dbSystem.client.db.dao.CardDao;
import org.salever.rcp.dbSystem.client.db.model.Card;
import org.salever.rcp.dbSystem.client.db.service.CardService;
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

	@Override
	public long countByCriterion(CardCriterion criterion) {
		return cardDao.countByCriterion(criterion);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.readidtech.data.client.db.service.CardService#delete(com.readidtech
	 * .data.client.db.model.Card)
	 */
	@Override
	public void delete(Card model) {
		cardDao.makeTransient(model);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.readidtech.data.client.db.service.CardService#findAll()
	 */
	@Override
	public List<Card> findAll() {
		return cardDao.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.readidtech.data.client.db.service.CardService#findById(int)
	 */
	@Override
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
	@Override
	public void insert(Card model) {
		cardDao.makePersistent(model);
	}

	@Override
	public List<Card> listByCriterion(CardCriterion criterion) {
		return cardDao.listByCriterion(criterion);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.readidtech.data.client.db.service.CardService#save(com.readidtech
	 * .data.client.db.model.Card)
	 */
	@Override
	public void save(Card model) {
		cardDao.makePersistent(model);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.readidtech.data.client.db.service.CardService#saveThenDeleteOthers
	 * (java.util.List)
	 */
	@Override
	public void saveThenDeleteOthers(List<Card> listToKeep) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.readidtech.data.client.db.service.CardService#update(com.readidtech
	 * .data.client.db.model.Card)
	 */
	@Override
	public void update(Card model) {
		cardDao.makePersistent(model);
	}

}

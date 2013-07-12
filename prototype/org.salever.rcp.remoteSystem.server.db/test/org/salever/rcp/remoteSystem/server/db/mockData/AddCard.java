package org.salever.rcp.remoteSystem.server.db.mockData;

import org.junit.Assert;
import org.junit.Test;
import org.salever.rcp.remoteSystem.server.db.CardDao;
import org.salever.rcp.remoteSystem.server.db.test.SpringTest;
import org.salever.rcp.remoteSystem.server.model.Card;

public class AddCard extends SpringTest {

	private CardDao dao = this.getBean(CardDao.class);

	@Test
	public void testSave() {

		Card entity = new Card();
		dao.makePersistent(entity);

		Assert.assertEquals(dao.findById(entity.getId()) != null, true);
	}
}

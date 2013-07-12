package org.salever.rcp.remoteSystem.server.db;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.salever.rcp.remoteSystem.server.db.CardDao;
import org.salever.rcp.remoteSystem.server.db.test.SpringTest;
import org.salever.rcp.remoteSystem.server.model.Card;
import org.salever.rcp.remoteSystem.server.model.ceriterion.CardCriterion;


public class TestCardDao extends SpringTest {

	private CardDao dao = this.getBean(CardDao.class);

	// @Test
	public void testSave() {
		Card card = new Card();
		card.setType(Card.TEACHER_TYPE);
		card.setUniqueCode("1111");
		card.setStatus(Card.STATUS_USED);
		dao.makePersistent(card);

		Assert.assertEquals(dao.findById(card.getId()) != null, true);

		dao.makeTransient(card);

		Assert.assertEquals(dao.findById(card.getId()) == null, true);
	}

	@Test
	public void testListByCriterion() {

		// 执行完一次就可以了，准备数据
		// for (int index = 0; index < 100; index++) {
		// Card card = new Card();
		// card.setType(Card.TEACHER_TYPE);
		// card.setUniqueCode("t" + index);
		// card.setStatus(Card.STATUS_UNUSED);
		// dao.makePersistent(card);
		// }

		CardCriterion criterion = new CardCriterion();
		criterion.setStatuses(new Integer[] { Card.STATUS_UNUSED });
		criterion.setTypes(new Integer[] { Card.TEACHER_TYPE, Card.PARENT_TYPE,
				Card.STUDENT_TYPE });
		long count = dao.countByCriterion(criterion);
		int pageSize = criterion.getPageSize();
		if (pageSize == 0) {
			pageSize = 30;// 默认的页面大小
		}
		long pages = count / pageSize; // 页数
		criterion.setCurrentPage(1);// 第几页，从1开始
		criterion.setPageCount(pages);// 共几页
		criterion.setPageSize(pageSize);// 页的大小
		List<Card> list = dao.listByCriterion(criterion);
		Assert.assertEquals(list.size(), 3);
	}
}

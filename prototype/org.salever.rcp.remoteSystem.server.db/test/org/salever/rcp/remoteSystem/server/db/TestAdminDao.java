package org.salever.rcp.remoteSystem.server.db;

import org.junit.Assert;
import org.junit.Test;
import org.salever.rcp.remoteSystem.server.db.AdminDao;
import org.salever.rcp.remoteSystem.server.db.test.SpringTest;
import org.salever.rcp.remoteSystem.server.model.Admin;


public class TestAdminDao extends SpringTest {

	private AdminDao dao = this.getBean(AdminDao.class);

	@Test
	public void testSave() {
		Admin admin = new Admin();
		admin.setEmail("aaa@111.com");
		admin.setUsername("test");
		admin.setPassword("est");
		admin.setMobile("13198765432");
		admin.setRealname("xxxx");
		admin.setType(Admin.ADMIN_TYPE);
		dao.makePersistent(admin);

		Assert.assertEquals(dao.findById(admin.getId()) != null, true);

		dao.makeTransient(admin);

		Assert.assertEquals(dao.findById(admin.getId()) != null, false);

	}
}

package org.salever.rcp.remoteSystem.server.db.init;

import org.junit.Test;
import org.salever.rcp.remoteSystem.server.db.AdminDao;
import org.salever.rcp.remoteSystem.server.db.test.SpringTest;
import org.salever.rcp.remoteSystem.server.model.Admin;


public class InitAdmin extends SpringTest {

	private AdminDao dao = this.getBean(AdminDao.class);

	@Test
	public void testSave() {

		dao.clean();

		Admin admin = new Admin();
		admin.setEmail("admin@company.com");
		admin.setUsername("admin");
		admin.setPassword("admin123");
		admin.setMobile("13198765432");
		admin.setRealname("系统管理员");
		admin.setType(Admin.ADMIN_TYPE);
		dao.makePersistent(admin);
	}
}

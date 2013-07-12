package org.salever.rcp.remoteSystem.server.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.salever.rcp.remoteSystem.server.service.AdminService;
import org.salever.rcp.remoteSystem.server.test.SpringTest;

public class TestCardService extends SpringTest {

	private AdminService service = this.getBean(AdminService.class);

	@Test
	public void testFindAll() {
		Assert.assertEquals(service.findAll().size(), 1);
	}

}

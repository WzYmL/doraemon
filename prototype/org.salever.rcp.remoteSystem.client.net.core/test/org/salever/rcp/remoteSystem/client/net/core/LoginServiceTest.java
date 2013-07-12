package org.salever.rcp.remoteSystem.client.net.core;

import junit.framework.Assert;

import org.junit.Test;
import org.salever.rcp.remoteSystem.server.model.Admin;
import org.salever.rcp.remoteSystem.server.service.AdminService;


public class LoginServiceTest extends SpringTest {

	AdminService userService = this.getBean(AdminService.class);

	@Test
	public final void testFindByUsername() {

		Admin user = userService.findByUsername("admin");
		Assert.assertEquals("admin123", user.getPassword());

	}

}

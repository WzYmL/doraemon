package org.salever.rcp.remoteSystem.server.security;

import org.salever.rcp.remoteSystem.server.model.Admin;
import org.salever.rcp.remoteSystem.server.service.LoginService;

public class LoginServiceImpl implements LoginService {

	@Override
	public Admin login() {
		Admin Admin = SessionHolder.getAdmin();
		return Admin;
	}

}

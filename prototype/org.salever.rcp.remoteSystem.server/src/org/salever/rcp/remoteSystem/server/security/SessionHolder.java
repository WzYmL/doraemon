package org.salever.rcp.remoteSystem.server.security;

import org.salever.rcp.remoteSystem.server.model.Admin;

public class SessionHolder {

	private static final ThreadLocal<Admin> tl = new ThreadLocal<Admin>();

	public static Admin getAdmin() {
		return tl.get();
	}

	protected static void setAdmin(Admin Admin) {
		tl.set(Admin);
	}

}

package org.salever.rcp.remoteSystem.server.security;

import org.salever.rcp.remoteSystem.server.model.Admin;
import org.springframework.security.core.GrantedAuthority;


public class GrantedAuthorityImpl implements GrantedAuthority {

	private Admin delegate;

	public GrantedAuthorityImpl(Admin Admin) {
		this.delegate = Admin;
	}

	public String getAuthority() {

		return "ROLE_Admin";

		// if (Boolean.TRUE.equals(delegate.getIsUser())) {
		// return "ROLE_User";
		// } else {
		// return "ROLE_Admin";
		// }
	}

}

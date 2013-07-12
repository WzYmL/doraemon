package org.salever.rcp.remoteSystem.server.security;

import org.salever.rcp.remoteSystem.server.model.Admin;
import org.salever.rcp.remoteSystem.server.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	AdminService userService;

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		UserDetailsImpl userDetails = null;

		Admin user = null;

		user = lookupUserFromDb(username);

		if (user != null) {
			userDetails = new UserDetailsImpl(user);
		}

		return userDetails;
	}

	private Admin lookupUserFromDb(String username) {
		Admin user = null;
		user = userService.findByUsername(username);
		return user;
	}

}

package org.salever.rcp.remoteSystem.server.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.salever.rcp.remoteSystem.server.model.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class UserDetailsImpl implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5424897749887458053L;

	private Admin user;
	private List<GrantedAuthority> roles;

	public UserDetailsImpl(Admin user) {
		this.user = user;
		this.roles = new ArrayList<GrantedAuthority>();
		this.roles.add(new GrantedAuthorityImpl(user));
	}

	public Admin getUser() {
		return user;
	}

	public Integer getUserId() {
		return user.getId();
	}

	public Collection<GrantedAuthority> getAuthorities() {
		return roles;
	}

	public String getPassword() {
		return user.getPassword();
	}

	public String getUsername() {
		return user.getUsername();
	}

	public boolean isAccountNonExpired() {
		return true;// TODO
	}

	public boolean isAccountNonLocked() {
		return true;// TODO
	}

	public boolean isCredentialsNonExpired() {
		return true;// TODO
	}

	public boolean isEnabled() {
		return true;// TODO
	}

}

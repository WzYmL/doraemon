package org.salever.rcp.remoteSystem.server.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.salever.rcp.remoteSystem.server.model.Admin;
import org.springframework.security.core.context.SecurityContextHolder;


public class SecurityFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;

		System.out.println("......Start handling request from "
				+ req.getRemoteUser() + " ......");

		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof UserDetailsImpl) {
			Admin user = ((UserDetailsImpl) principal).getUser();
			SessionHolder.setAdmin(user);
		} else {
			System.out.println("not supported principal: + "
					+ principal.getClass());
			return;
		}

		chain.doFilter(request, response);

		System.out.println("......Finished handling request from "
				+ req.getRemoteUser() + " ......");
	}

	public void init(FilterConfig config) throws ServletException {

	}

}

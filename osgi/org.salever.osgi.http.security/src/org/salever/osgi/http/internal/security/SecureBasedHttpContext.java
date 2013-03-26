/*******************************************************************************
 * Copyright (c) 2010 liXiaopeng. All rights reserved. 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *
 * Contributors:
 *     LiXiaopeng - initial API and implementation
 *
 * Create on May 15, 2012 2:42:10 PM
 *******************************************************************************/
package org.salever.osgi.http.internal.security;

import java.io.IOException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.http.HttpContext;

/**
 * @author LiXP
 * 
 */
public class SecureBasedHttpContext implements HttpContext {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.service.http.HttpContext#handleSecurity(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public boolean handleSecurity(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.service.http.HttpContext#getResource(java.lang.String)
	 */
	@Override
	public URL getResource(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.service.http.HttpContext#getMimeType(java.lang.String)
	 */
	@Override
	public String getMimeType(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}

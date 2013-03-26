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
 * Create on Apr 28, 2012 4:09:59 PM
 *******************************************************************************/
package org.equinoxosgi.toast.internal.backend.emergency;

import org.equinoxosgi.toast.backend.emergency.EmergencyServlet;
import org.equinoxosgi.toast.core.ICoreConstants;
import org.equinoxosgi.toast.core.LogUtility;
import org.equinoxosgi.toast.core.PropertyManager;
import org.equinoxosgi.toast.core.UrlBuilder;
import org.equinoxosgi.toast.core.emergency.IEmergencyConstants;
import org.osgi.service.http.HttpService;

/**
 * @author LiXP
 * 
 */
public class Component {

	private HttpService http;

	private String servletAlias;

	public void setServletAlias(String servletAlias) {
		this.servletAlias = servletAlias;
	}

	public String getServletAlias() {
		return servletAlias;
	}

	public void setHttp(HttpService http) {
		this.http = http;
	}

	public HttpService getHttp() {
		return http;
	}

	public void startup() {

		try {
			String servletRoot = PropertyManager.getProperty(
					ICoreConstants.BACK_END_URL_PROPERTY,
					ICoreConstants.BACK_END_URL_DEFAULT);

			UrlBuilder urlBuilder = new UrlBuilder(servletRoot);
			urlBuilder.appendPath(IEmergencyConstants.EMERGENCY_FUNCTION);
			servletAlias = urlBuilder.getPath();

			EmergencyServlet emergencyServlet = new EmergencyServlet();
			http.registerServlet(servletAlias, emergencyServlet, null, null);

			LogUtility.logInfo(this, "Register EmergencyServlet at "
					+ servletAlias);
		} catch (Exception e) {
			LogUtility.logError(this,
					"Error when register servlet with HttpService ", e);
		}

	}

	public void shutdown() {
		http.unregister(servletAlias);
	}
}

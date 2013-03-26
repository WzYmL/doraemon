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
 * Create on Apr 28, 2012 3:55:28 PM
 *******************************************************************************/
package org.equinoxosgi.toast.backend.emergency;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.equinoxosgi.toast.core.ICoreConstants;
import org.equinoxosgi.toast.core.LogUtility;
import org.equinoxosgi.toast.core.emergency.IEmergencyConstants;

/**
 * @author LiXP
 * 
 */
public class EmergencyServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = getParameter(req, resp, ICoreConstants.ID_PARAMETER);
		int latitude = Integer.parseInt(getParameter(req, resp,
				IEmergencyConstants.LATITUDE_PARAMETER));
		int longitude = Integer.parseInt(getParameter(req, resp,
				IEmergencyConstants.LONGITUDE_PARAMETER));
		int speed = Integer.parseInt(getParameter(req, resp,
				IEmergencyConstants.SPEED_PARAMETER));
		int heading = Integer.parseInt(getParameter(req, resp,
				IEmergencyConstants.HEADING_PARAMETER));

		double lat = latitude / 1000.0;
		double lon = longitude / 1000.0;

		LogUtility.logInfo(this, "Emergency: " + id + " (at " + lat + "N, "
				+ lon + "E) " + heading + "dkg " + speed + "kph");
		PrintWriter writer = resp.getWriter();
		writer.write("Help is on its way");

		resp.setContentType(ICoreConstants.CONTENT_TYPE_PLAIN);
	}

	private String getParameter(HttpServletRequest req,
			HttpServletResponse resp, String name) throws IOException,
			ServletException {
		String parameter = req.getParameter(name);
		if (parameter == null || parameter.isEmpty()) {
			resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE,
					ICoreConstants.MISSING_PARAMETER + name);
			throw new ServletException(ICoreConstants.MISSING_PARAMETER + name);
		}
		return parameter;
	}
}

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
 * Create on Apr 24, 2012 3:23:56 PM
 *******************************************************************************/
package org.equinoxogsi.toast.client.emergency;

import org.equinoxogsi.toast.dev.airbag.IAirbag;
import org.equinoxogsi.toast.dev.airbag.IAirbagListener;
import org.equinoxogsi.toast.dev.gps.IGps;

/**
 * @author LiXP
 * 
 */
public class EmergencyMonitor implements IAirbagListener {

	private IAirbag airbag;

	private IGps gps;

	@Override
	public void deployed() {
		System.out.println("Emergency occurred at lat=" + gps.getLatitude()
				+ " lon=" + gps.getLongitude() + " heading=" + gps.getHeading()
				+ " speed=" + gps.getSpeed());

	}

	/**
	 * @param airbag
	 *            the airbag to set
	 */
	public void setAirbag(IAirbag airbag) {
		this.airbag = airbag;
	}

	/**
	 * @param gps
	 *            the gps to set
	 */
	public void setGps(IGps gps) {
		this.gps = gps;
	}

	public void startup() {
		airbag.addListener(this);

		airbag.startup();
	}

	public void shutdown() {
		airbag.removeListener(this);
	}
}

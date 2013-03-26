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
 * Create on Apr 24, 2012 3:03:44 PM
 *******************************************************************************/
package org.equinoxogsi.toast.internal.dev.gps;

import org.equinoxogsi.toast.dev.gps.IGps;

/**
 * @author LiXP
 * 
 */
public class FakeGps implements IGps {

	public int getHeading() {
		return 90;
	}

	public int getLatitude() {
		return 3776999;
	}

	public int getLongitude() {
		return -12244694;
	}

	public int getSpeed() {
		return 50;
	}
}

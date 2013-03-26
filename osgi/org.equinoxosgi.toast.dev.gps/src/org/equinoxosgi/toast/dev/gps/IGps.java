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
 * Create on Apr 24, 2012 5:13:24 PM
 *******************************************************************************/
package org.equinoxosgi.toast.dev.gps;

/**
 * @author LiXP
 * 
 */
public interface IGps {

	public int getHeading();

	public int getLatitude();

	public int getLongitude();

	public int getSpeed();
}

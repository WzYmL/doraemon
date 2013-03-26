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
 * Create on Apr 6, 2012 1:59:54 PM
 *******************************************************************************/
package org.salever.osgi.depends.service;

import org.salever.osgi.depends.sdk2.Activator;

/**
 * @author LiXP
 * 
 */
public class SDKService {

	public static void service() {
		System.out.println(Activator.class.toString() + ": service!");
	}
}

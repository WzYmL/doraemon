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
 * Create on Apr 24, 2012 3:18:17 PM
 *******************************************************************************/
package org.equinoxosgi.toast.dev.airbag;

/**
 * @author LiXP
 * 
 */
public interface IAirbag {

	public void addListener(IAirbagListener listener);

	public void removeListener(IAirbagListener listener);

	public void deploy();

}

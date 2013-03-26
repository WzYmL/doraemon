/*******************************************************************************
 * Copyright (c) 2009 Paul VanderLei, Simon Archer, Jeff McAffer and others. All 
 * rights reserved. This program and the accompanying materials are made available 
 * under the terms of the Eclipse Public License v1.0 and Eclipse Distribution License
 * v1.0 which accompanies this distribution. The Eclipse Public License is available at 
 * http://www.eclipse.org/legal/epl-v10.html and the Eclipse Distribution License 
 * is available at http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors: 
 *     Paul VanderLei, Simon Archer, Jeff McAffer - initial API and implementation
 *******************************************************************************/
package org.equinoxosgi.toast.core.tickle;

import java.net.URI;

public interface ITickleReceiver {
	public void addListener(ITickleListener listener);

	public void removeListener(ITickleListener listener);

	/**
	 * Return the descriptor of how people should tickle this receiver.
	 * @return a description of how to tickle this receiver.
	 */
	public URI getTalkbackDescriptor();
}

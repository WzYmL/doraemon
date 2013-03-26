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
package org.equinoxosgi.toast.backend.controlcenter;

import java.util.Collection;
import org.equinoxosgi.toast.backend.data.IVehicle;

public interface IData {
	public Collection getVehicles();

	public void update(IVehicle object);

	public void persist(IVehicle object);

	public void removeVehicle(String name);

	public Collection getVehicleNames();

	public IVehicle getVehicle(String name);
}

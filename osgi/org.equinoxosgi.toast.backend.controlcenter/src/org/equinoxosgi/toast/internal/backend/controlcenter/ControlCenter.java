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
package org.equinoxosgi.toast.internal.backend.controlcenter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.equinoxosgi.toast.backend.controlcenter.IControlCenter;
import org.equinoxosgi.toast.backend.controlcenter.IData;
import org.equinoxosgi.toast.backend.data.IToastBackEndDataFactory;
import org.equinoxosgi.toast.backend.data.IVehicle;
import org.equinoxosgi.toast.backend.data.internal.Vehicle;
import org.equinoxosgi.toast.backend.provisioning.IProvisioner;
import org.equinoxosgi.toast.core.LogUtility;
import org.equinoxosgi.toast.core.discovery.IDiscovery;
import org.equinoxosgi.toast.core.discovery.IDiscoveryListener;

public class ControlCenter implements IControlCenter, IDiscoveryListener {
	private IProvisioner provisioner;
	private IDiscovery discovery;
	private IData data;

	public ControlCenter() {
		super();
	}

	private void loadData() {
		Collection vehicles = data.getVehicles();
		for (Iterator i = vehicles.iterator(); i.hasNext();) {
			IVehicle vehicle = (IVehicle) i.next();
			Map properties = new HashMap();
			properties.put("osgi.os", System.getProperty("osgi.os"));
			properties.put("osgi.ws", System.getProperty("osgi.ws"));
			properties.put("osgi.arch", System.getProperty("osgi.arch"));
			provisioner.addProfile(vehicle.getName(), properties);
		}
	}

	public void setDiscovery(IDiscovery value) {
		discovery = value;
	}

	public void setData(IData value) {
		data = value;
	}

	public void setProvisioner(IProvisioner value) {
		provisioner = value;
	}

	public void startup() {
		Runnable work = new Runnable() {
			public void run() {
				loadData();
				discovery.addListener(ControlCenter.this);
				Collection profiles = provisioner.getProfiles();
				for (Iterator i = profiles.iterator(); i.hasNext();)
					addVehicle((String) i.next(), null);
			}
		};
		new Thread(work).run();
	}

	public void shutdown() {
		discovery.removeListener(this);
	}

	public Collection getKnownIds() {
		return data.getVehicleNames();
	}

	public Collection getVehicles() {
		return new ArrayList(data.getVehicles());
	}

	public IVehicle getVehicle(String name) {
		return data.getVehicle(name);
	}

	public IVehicle addVehicle(String name, Map properties) {
		IVehicle result = getVehicle(name);
		if (result != null) {
			return result;
		}
		Vehicle vehicle = (Vehicle) IToastBackEndDataFactory.eINSTANCE.createVehicle();
		vehicle.setName(name);
		data.persist(vehicle);
		provisioner.addProfile(name, properties);
		return vehicle;
	}

	// TODO Semantics of removeVehicle are unclear as this method is never called
	public void removeVehicle(String name) {
		provisioner.removeProfile(name);
	}

	public void registered(String id, Map properties) {
		LogUtility.logDebug(this, "Vehicle registered: " + id);
		IVehicle vehicle = addVehicle(id, properties);
		vehicle.setOnline(true);
		data.update(vehicle);
	}

	public void unregistered(String name) {
		LogUtility.logDebug(this, "Vehicle unregistered: " + name);
		IVehicle vehicle = addVehicle(name, null);
		vehicle.setOnline(false);
		data.update(vehicle);
	}
}

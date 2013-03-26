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
package org.equinoxosgi.toast.devsim.fw;

import java.io.PrintWriter;

public abstract class SimulatedParameter {
	protected String name;
	protected String label;

	public SimulatedParameter(String name, String label) {
		super();
		this.name = name;
		this.label = label;
	}

	public String getName() {
		return name;
	}

	public abstract void performAction(String action);

	public boolean generateRefreshValue(PrintWriter writer, String prefix) {
		return false;
	}

	public void resetToDefault() {
	}

	public void run() {
	}

	public abstract void generate(HtmlGenerator gen);
}
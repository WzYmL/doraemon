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
package org.salever.osgi.http.security.util;

import java.util.HashMap;
import java.util.Map;

public class PropertyManager {
	private static final PropertyManager INSTANCE = new PropertyManager();
	private Map cache;

	public static String getProperty(String property, String defaultValue) {
		return PropertyManager.INSTANCE.instanceGetProperty(property,
				defaultValue);
	}

	public static String getProperty(String property) {
		return PropertyManager.INSTANCE.instanceGetProperty(property);
	}

	public static boolean getBooleanProperty(String property,
			boolean defaultValue) {
		return PropertyManager.INSTANCE.instanceGetBooleanProperty(property,
				defaultValue);
	}

	public static boolean getBooleanProperty(String property) {
		return PropertyManager.INSTANCE.instanceGetBooleanProperty(property);
	}

	// Private
	private PropertyManager() {
		super();
		cache = new HashMap(11);
	}

	private String instanceGetProperty(String property, String defaultValue) {
		if (property == null) {
			throw new IllegalArgumentException("property must not be null");
		}
		String value;
		synchronized (cache) {
			value = (String) cache.get(property);
			if (value == null) {
				value = System.getProperty(property, defaultValue);
				System.out.println("Property: -D" + property + '=' + value); //$NON-NLS-1$
				if (value != null) {
					cache.put(property, value);
				}
			}
		}
		return value;
	}

	private String instanceGetProperty(String property) {
		String value = instanceGetProperty(property, null);
		return value;
	}

	private boolean instanceGetBooleanProperty(String property,
			boolean defaultValue) {
		String defaultValueString = String.valueOf(defaultValue);
		String value = instanceGetProperty(property, defaultValueString);
		Boolean wrapper = Boolean.valueOf(value);
		boolean state = wrapper.booleanValue();
		return state;
	}

	private boolean instanceGetBooleanProperty(String property) {
		boolean value = instanceGetBooleanProperty(property, false);
		return value;
	}
}

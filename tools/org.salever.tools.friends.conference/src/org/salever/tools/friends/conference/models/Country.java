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
 * Create on May 31, 2012 10:51:45 AM
 *******************************************************************************/
package org.salever.tools.friends.conference.models;

/**
 * @author LiXP
 * 
 */
public class Country {

	private String name;

	private int callCount;

	/**
	 * @param name
	 */
	public Country(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Country) {
			if (name != null) {
				return name.equals(((Country) obj).getName());
			}
		}
		return super.equals(obj);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		if (name != null) {
			return name.hashCode();
		}
		return super.hashCode();
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		if (name != null) {
			return "{" + name + "}";
		}
		return super.toString();
	}

	/**
	 * @param callCount
	 *            the callCount to set
	 */
	public void setCallCount(int callCount) {
		this.callCount = callCount;
	}

	/**
	 * @return the callCount
	 */
	public int getCallCount() {
		return callCount;
	}

}

/*******************************************************************************
 * Copyright (c) 2003, 2010 salever@126.com All rights reserved. 
 * 
 *
 * @author salever@126.com
 * @created 2010-5-4 上午10:36:39
 * @version 
 * @description 
 *******************************************************************************/
package org.salever.rcp.tools.topology_viewer.models;

/**
 * @description
 * 
 * @author salever@126.com
 * 
 */
public class Interface extends AbstractModel {

	private String ipAddress;
	private String name;
	private String subnetMask;

	/**
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the subnetMask
	 */
	public String getSubnetMask() {
		return subnetMask;
	}

	/**
	 * @param ipAddress
	 *            the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param subnetMask
	 *            the subnetMask to set
	 */
	public void setSubnetMask(String subnetMask) {
		this.subnetMask = subnetMask;
	}

}

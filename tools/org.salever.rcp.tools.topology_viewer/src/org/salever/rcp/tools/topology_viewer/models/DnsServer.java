/*******************************************************************************
 * Copyright (c) 2003, 2010 salever@126.com All rights reserved. 
 * 
 *
 * @author salever@126.com
 * @created 2010-5-4 上午10:36:12
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
public class DnsServer extends AbstractModel {

	private String ipAddress;

	/**
	 * @param ipAddress
	 */
	public DnsServer(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * @param ipAddress
	 *            the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

}

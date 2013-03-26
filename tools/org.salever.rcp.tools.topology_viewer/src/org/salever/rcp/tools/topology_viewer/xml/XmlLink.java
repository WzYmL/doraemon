/*******************************************************************************
 * Copyright (c) 2003, 2010 salever@126.com All rights reserved. 
 * 
 *
 * @author salever@126.com
 * @created 2010-4-30 下午12:34:29
 * @version 
 * @description 
 *******************************************************************************/
package org.salever.rcp.tools.topology_viewer.xml;

/**
 * @description
 * 
 * @author salever@126.com
 * 
 */
public class XmlLink {

	private int localRouter;

	private int remoteRouter;

	/**
	 * @return the localRouter
	 */
	public int getLocalRouter() {
		return localRouter;
	}

	/**
	 * @return the remoteRouter
	 */
	public int getRemoteRouter() {
		return remoteRouter;
	}

	/**
	 * @param localRouter
	 *            the localRouter to set
	 */
	public void setLocalRouter(int localRouter) {
		this.localRouter = localRouter;
	}

	/**
	 * @param remoteRouter
	 *            the remoteRouter to set
	 */
	public void setRemoteRouter(int remoteRouter) {
		this.remoteRouter = remoteRouter;
	}

}

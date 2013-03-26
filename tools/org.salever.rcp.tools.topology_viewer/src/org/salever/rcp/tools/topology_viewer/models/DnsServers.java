/*******************************************************************************
 * Copyright (c) 2003, 2010 salever@126.com All rights reserved. 
 * 
 *
 * @author salever@126.com
 * @created 2010-5-4 下午04:14:14
 * @version 
 * @description 
 *******************************************************************************/
package org.salever.rcp.tools.topology_viewer.models;

import java.util.ArrayList;
import java.util.List;

/**
 * @description
 * 
 * @author salever@126.com
 * 
 */
public class DnsServers extends AbstractModel {

	public static final String P_CHILD = "p_child";

	private List<DnsServer> children;

	public DnsServers() {
		children = new ArrayList<DnsServer>();
	}

	public void addChild(DnsServer obj) {
		this.children.add(obj);
		firePropertyChange(P_CHILD, null, null);
	}

	/**
	 * @return the children
	 */
	public List<DnsServer> getChildren() {
		return children;
	}

	public void removeChild(DnsServer obj) {
		this.children.remove(obj);
	}

	/**
	 * @param children
	 *            the children to set
	 */
	public void setChildren(List<DnsServer> children) {
		this.children = children;
		firePropertyChange(P_CHILD, null, null);
	}

}

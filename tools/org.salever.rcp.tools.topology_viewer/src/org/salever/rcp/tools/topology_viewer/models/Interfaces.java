/*******************************************************************************
 * Copyright (c) 2003, 2010 salever@126.com All rights reserved. 
 * 
 *
 * @author salever@126.com
 * @created 2010-5-4 下午04:25:08
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
public class Interfaces extends AbstractModel {

	public static final String P_CHILD = "p_child";

	private List<Interface> children;

	public Interfaces() {
		children = new ArrayList<Interface>();
	}

	public void addChild(Interface obj) {
		this.children.add(obj);
		firePropertyChange(P_CHILD, null, null);
	}

	/**
	 * @return the children
	 */
	public List<Interface> getChildren() {
		return children;
	}

	public void removeChild(Interface obj) {
		this.children.remove(obj);
	}

	/**
	 * @param children
	 *            the children to set
	 */
	public void setChildren(List<Interface> children) {
		this.children = children;
		firePropertyChange(P_CHILD, null, null);
	}

}

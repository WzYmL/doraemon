/*******************************************************************************
 * Copyright (c) 2003, 2010 salever@126.com All rights reserved. 
 * 
 *
 * @author salever@126.com
 * @created 2010-4-30 下午12:33:08
 * @version 
 * @description 
 *******************************************************************************/
package org.salever.rcp.tools.topology_viewer.xml;

import java.util.ArrayList;
import java.util.List;

/**
 * @description
 * 
 * @author salever@126.com
 * 
 */
public class XmlSubnet {

	private int id;

	private int level;

	private String name;

	private String type;

	private XmlIcon icon;

	private List<XmlSubnetRef> subnets = new ArrayList<XmlSubnetRef>();

	private List<XmlRouter> routers = new ArrayList<XmlRouter>();

	public void addRouter(XmlRouter router) {
		routers.add(router);
	}

	public void addSubnet(XmlSubnetRef subnet) {
		subnets.add(subnet);
	}

	/**
	 * @return the icon
	 */
	public XmlIcon getIcon() {
		return icon;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the routers
	 */
	public List<XmlRouter> getRouters() {
		return routers;
	}

	/**
	 * @return the subnets
	 */
	public List<XmlSubnetRef> getSubnets() {
		return subnets;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param icon
	 *            the icon to set
	 */
	public void setIcon(XmlIcon icon) {
		this.icon = icon;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param routers
	 *            the routers to set
	 */
	public void setRouters(List<XmlRouter> routers) {
		this.routers = routers;
	}

	/**
	 * @param subnets
	 *            the subnets to set
	 */
	public void setSubnets(List<XmlSubnetRef> subnets) {
		this.subnets = subnets;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

}

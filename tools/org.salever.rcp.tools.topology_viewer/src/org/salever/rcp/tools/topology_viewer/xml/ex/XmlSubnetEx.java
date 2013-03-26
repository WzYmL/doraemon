/*******************************************************************************
 * Copyright (c) 2003, 2010 salever@126.com All rights reserved. 
 * 
 *
 * @author salever@126.com
 * @created 2010-5-1 下午01:24:38
 * @version 
 * @description 
 *******************************************************************************/
package org.salever.rcp.tools.topology_viewer.xml.ex;

import java.util.ArrayList;
import java.util.List;

/**
 * @description
 * 
 * @author salever@126.com
 * 
 */
public class XmlSubnetEx {

	private int id;

	private int level;

	private String name;

	private String type;

	private XmlIconEx icon;

	private List<XmlRouterEx> routers = new ArrayList<XmlRouterEx>();

	public void addRouter(XmlRouterEx xmlRouterEx) {
		routers.add(xmlRouterEx);
	}

	/**
	 * @return the icon
	 */
	public XmlIconEx getIcon() {
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
	public List<XmlRouterEx> getRouters() {
		return routers;
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
	public void setIcon(XmlIconEx icon) {
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
	public void setRouters(List<XmlRouterEx> routers) {
		this.routers = routers;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

}

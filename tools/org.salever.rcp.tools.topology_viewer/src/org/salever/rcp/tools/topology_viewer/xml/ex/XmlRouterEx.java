/*******************************************************************************
 * Copyright (c) 2003, 2010 salever@126.com All rights reserved. 
 * 
 *
 * @author salever@126.com
 * @created 2010-5-1 下午01:24:48
 * @version 
 * @description 
 *******************************************************************************/
package org.salever.rcp.tools.topology_viewer.xml.ex;


/**
 * @description
 * 
 * @author salever@126.com
 * 
 */
public class XmlRouterEx {

	private int id;

	private String name;

	private String type;

	private XmlIconEx icon;

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
	 * @return the name
	 */
	public String getName() {
		return name;
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
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

}

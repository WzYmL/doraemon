/*******************************************************************************
 * Copyright (c) 2003, 2010 salever@126.com All rights reserved. 
 * 
 *
 * @author salever@126.com
 * @created 2010-4-30 下午12:24:37
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
public class XmlConfig {

	private XmlMappingFile mappingFile;
	private List<XmlSubnet> subnets = new ArrayList<XmlSubnet>();
	private List<XmlRouter> routers = new ArrayList<XmlRouter>();
	private List<XmlLink> links = new ArrayList<XmlLink>();

	public void addLink(XmlLink link) {
		links.add(link);
	}

	public void addRouter(XmlRouter router) {
		routers.add(router);
	}

	public void addSubnet(XmlSubnet subnet) {
		subnets.add(subnet);
	}

	/**
	 * @return the links
	 */
	public List<XmlLink> getLinks() {
		return links;
	}

	/**
	 * @return the mappingFile
	 */
	public XmlMappingFile getMappingFile() {
		return mappingFile;
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
	public List<XmlSubnet> getSubnets() {
		return subnets;
	}

	/**
	 * @param links
	 *            the links to set
	 */
	public void setLinks(List<XmlLink> links) {
		this.links = links;
	}

	/**
	 * @param mappingFile
	 *            the mappingFile to set
	 */
	public void setMappingFile(XmlMappingFile mappingFile) {
		this.mappingFile = mappingFile;
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
	public void setSubnets(List<XmlSubnet> subnets) {
		this.subnets = subnets;
	}

}

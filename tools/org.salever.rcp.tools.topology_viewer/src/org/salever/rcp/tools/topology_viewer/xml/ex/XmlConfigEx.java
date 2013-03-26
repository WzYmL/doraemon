/*******************************************************************************
 * Copyright (c) 2003, 2010 salever@126.com All rights reserved. 
 * 
 *
 * @author salever@126.com
 * @created 2010-5-1 下午01:23:47
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
public class XmlConfigEx {

	private XmlMappingFileEx mappingFile;

	private List<XmlSubnetEx> subnets = new ArrayList<XmlSubnetEx>();

	private List<XmlRouterEx> routers = new ArrayList<XmlRouterEx>();

	public void addRouter(XmlRouterEx router) {
		routers.add(router);
	}

	public void addSubnet(XmlSubnetEx subnet) {
		subnets.add(subnet);
	}

	/**
	 * @return the mappingFile
	 */
	public XmlMappingFileEx getMappingFile() {
		return mappingFile;
	}

	/**
	 * @return the routers
	 */
	public List<XmlRouterEx> getRouters() {
		return routers;
	}

	/**
	 * @return the subnets
	 */
	public List<XmlSubnetEx> getSubnets() {
		return subnets;
	}

	/**
	 * @param mappingFile
	 *            the mappingFile to set
	 */
	public void setMappingFile(XmlMappingFileEx mappingFile) {
		this.mappingFile = mappingFile;
	}

	/**
	 * @param routers
	 *            the routers to set
	 */
	public void setRouters(List<XmlRouterEx> routers) {
		this.routers = routers;
	}

	/**
	 * @param subnets
	 *            the subnets to set
	 */
	public void setSubnets(List<XmlSubnetEx> subnets) {
		this.subnets = subnets;
	}

}

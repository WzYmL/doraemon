/*******************************************************************************
 * Copyright (c) 2003, 2010 salever@126.com All rights reserved. 
 * 
 *
 * @author salever@126.com
 * @created 2010-4-30 下午12:33:28
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
public class XmlRouter {

	private int id;

	private String name;

	private String type;

	private XmlIcon icon;

	private XmlConfigFile configFile;

	private XmlRoutingProtocol routingProtocol;

	private List<XmlDnsServer> dnsServers = new ArrayList<XmlDnsServer>();

	private List<XmlInterface> netInterfaces = new ArrayList<XmlInterface>();

	public void addDnsServer(XmlDnsServer dnsServer) {
		dnsServers.add(dnsServer);
	}

	public void addNetInterface(XmlInterface netInterface) {
		netInterfaces.add(netInterface);
	}

	/**
	 * @return the configFile
	 */
	public XmlConfigFile getConfigFile() {
		return configFile;
	}

	/**
	 * @return the dnsServers
	 */
	public List<XmlDnsServer> getDnsServers() {
		return dnsServers;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the netInterfaces
	 */
	public List<XmlInterface> getNetInterfaces() {
		return netInterfaces;
	}

	/**
	 * @return the routingProtocol
	 */
	public XmlRoutingProtocol getRoutingProtocol() {
		return routingProtocol;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param configFile
	 *            the configFile to set
	 */
	public void setConfigFile(XmlConfigFile configFile) {
		this.configFile = configFile;
	}

	/**
	 * @param dnsServers
	 *            the dnsServers to set
	 */
	public void setDnsServers(List<XmlDnsServer> dnsServers) {
		this.dnsServers = dnsServers;
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
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param netInterfaces
	 *            the netInterfaces to set
	 */
	public void setNetInterfaces(List<XmlInterface> netInterfaces) {
		this.netInterfaces = netInterfaces;
	}

	/**
	 * @param routingProtocol
	 *            the routingProtocol to set
	 */
	public void setRoutingProtocol(XmlRoutingProtocol routingProtocol) {
		this.routingProtocol = routingProtocol;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

}

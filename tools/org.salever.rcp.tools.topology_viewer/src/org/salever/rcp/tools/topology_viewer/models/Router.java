/*******************************************************************************
 * Copyright (c) 2003, 2010 salever@126.com All rights reserved. 
 * 
 *
 * @author salever@126.com
 * @created 2010-5-4 上午10:38:07
 * @version 
 * @description 
 *******************************************************************************/
package org.salever.rcp.tools.topology_viewer.models;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.IEditorInput;

/**
 * @description
 * 
 * @author salever@126.com
 * 
 */
public class Router extends TopoModel {

	public static final String P_INTERFACES = "p_interfaces";

	public static final String P_DNS_SERVERS = "p_snd_servers";

	public static final String P_CONFIG_FILE = "p_config_file";

	public static final String P_ROUTING_PROTOCOL = "p_routing_protocol";

	public static final String P_CHILDREN = "p_children";

	private List<Interface> interfaces;

	private List<DnsServer> dnsServers;

	private String configFile;

	private RoutingProtocol protocol;

	private IEditorInput input;

	public Router() {
		super();
		interfaces = new ArrayList<Interface>();
		dnsServers = new ArrayList<DnsServer>();
		protocol = new RoutingProtocol();
	}

	public void addDnsServer(DnsServer obj) {
		this.dnsServers.add(obj);
		firePropertyChange(P_CHILDREN, null, null);
	}

	public void addInterface(Interface obj) {
		this.interfaces.add(obj);
		firePropertyChange(P_CHILDREN, null, null);
	}

	/**
	 * @return the configFile
	 */
	public String getConfigFile() {
		return configFile;
	}

	/**
	 * @return the dnsServers
	 */
	public List<DnsServer> getDnsServers() {
		return dnsServers;
	}

	/**
	 * @return the input
	 */
	public IEditorInput getInput() {
		return input;
	}

	/**
	 * @return the interfaces
	 */
	public List<Interface> getInterfaces() {
		return interfaces;
	}

	/**
	 * @return the protocol
	 */
	public RoutingProtocol getProtocol() {
		return protocol;
	}

	public void removeDnsServer(DnsServer obj) {
		this.dnsServers.remove(obj);
		firePropertyChange(P_DNS_SERVERS, null, null);
	}

	public void removeInterface(Interface obj) {
		this.interfaces.remove(obj);
		firePropertyChange(P_INTERFACES, null, null);
	}

	/**
	 * @param configFile
	 *            the configFile to set
	 */
	public void setConfigFile(String configFile) {
		this.configFile = configFile;
		firePropertyChange(P_CONFIG_FILE, null, null);
	}

	/**
	 * @param dnsServers
	 *            the dnsServers to set
	 */
	public void setDnsServers(List<DnsServer> dnsServers) {
		this.dnsServers = dnsServers;
	}

	/**
	 * @param input
	 *            the input to set
	 */
	public void setInput(IEditorInput input) {
		this.input = input;
	}

	/**
	 * @param interfaces
	 *            the interfaces to set
	 */
	public void setInterfaces(List<Interface> interfaces) {
		this.interfaces = interfaces;
	}

	/**
	 * @param protocol
	 *            the protocol to set
	 */
	public void setProtocol(RoutingProtocol protocol) {
		this.protocol.setExteriorGatewayProtocol(protocol.getExteriorGatwayProtocol());
		this.protocol.setInteriorGatewayProtocol(protocol.getInteriorGatewayProtocol());
		firePropertyChange(P_CHILDREN, null, null);
	}

}

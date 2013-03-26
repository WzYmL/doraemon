/*******************************************************************************
 * Copyright (c) 2003, 2010 salever@126.com All rights reserved. 
 * 
 *
 * @author salever@126.com
 * @created 2010-5-4 上午11:35:24
 * @version 
 * @description 
 *******************************************************************************/
package org.salever.rcp.tools.topology_viewer.models;

/**
 * @description
 * 
 * @author salever@126.com
 * 
 */
public class RoutingProtocol extends AbstractModel {

	public static final String P_EXTERIOR_GATEWAY_PROTOCOL = "p_exterior_gateway_protocol";

	public static final String P_INTERIOR_GATEWAY_PROTOCOL = "p_interiro_gateway_protocol";

	private String exteriorGatwayProtocol = "";

	private String interiorGatewayProtocol = "";

	/**
	 * @return the exteriorGatwayProtocol
	 */
	public String getExteriorGatwayProtocol() {
		return exteriorGatwayProtocol;
	}

	/**
	 * @return the interiorGatewayProtocol
	 */
	public String getInteriorGatewayProtocol() {
		return interiorGatewayProtocol;
	}

	/**
	 * @param exteriorGatwayProtocol
	 *            the exteriorGatwayProtocol to set
	 */
	public void setExteriorGatewayProtocol(String exteriorGatwayProtocol) {
		this.exteriorGatwayProtocol = exteriorGatwayProtocol;
	}

	/**
	 * @param interiorGatewayProtocol
	 *            the interiorGatewayProtocol to set
	 */
	public void setInteriorGatewayProtocol(String interiorGatewayProtocol) {
		this.interiorGatewayProtocol = interiorGatewayProtocol;
	}
}

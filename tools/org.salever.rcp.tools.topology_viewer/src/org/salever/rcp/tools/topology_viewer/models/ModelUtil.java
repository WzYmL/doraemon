/*******************************************************************************
 * Copyright (c) 2003, 2010 salever@126.com All rights reserved. 
 * 
 *
 * @author salever@126.com
 * @created 2010-5-6 上午10:45:43
 * @version 
 * @description 
 *******************************************************************************/
package org.salever.rcp.tools.topology_viewer.models;

import java.util.Iterator;

/**
 * @description
 * 
 * @author salever@126.com
 * 
 */
public class ModelUtil {

	public static Network transform(Subnet subnet) {

		Network network = new Network();
		network.setContent(subnet);
		network.setName(subnet.getName());
        network.setLevel(subnet.getLevel());
        network.setId(subnet.getId());
		for (Iterator<Router> it = subnet.getRouters().iterator(); it.hasNext();) {
			network.addChild(it.next());
		}

		for (Iterator<Subnet> it = subnet.getSubnets().iterator(); it.hasNext();) {
			network.addChild(it.next());
		}
		return network;
	}

}

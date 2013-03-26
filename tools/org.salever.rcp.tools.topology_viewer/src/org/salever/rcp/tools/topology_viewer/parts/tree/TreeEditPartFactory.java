/**
 * @file TreeEditpartFactory.java
 *
 * @author salever@126.com
 * 
 * @version 
 * 
 * @Create on 2009-7-14  ����03:29:13
 * 
 * @description 
 * 
 */
package org.salever.rcp.tools.topology_viewer.parts.tree;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.salever.rcp.tools.topology_viewer.models.DnsServer;
import org.salever.rcp.tools.topology_viewer.models.DnsServers;
import org.salever.rcp.tools.topology_viewer.models.Interface;
import org.salever.rcp.tools.topology_viewer.models.Interfaces;
import org.salever.rcp.tools.topology_viewer.models.Network;
import org.salever.rcp.tools.topology_viewer.models.Router;
import org.salever.rcp.tools.topology_viewer.models.RoutingProtocol;
import org.salever.rcp.tools.topology_viewer.models.Subnet;


/**
 * @author salever@126.com
 * 
 * @name TreeEditpartFactory
 * 
 */
public class TreeEditPartFactory implements EditPartFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart,
	 * java.lang.Object)
	 */
	@Override
	public EditPart createEditPart(EditPart context, Object model) {

		EditPart part = null;

		if (model instanceof Router) {
			part = new RouterTreeEditPart();
		} else if (model instanceof Network) {
			part = new NetworkTreeEditPart();
		} else if (model instanceof RoutingProtocol) {
			part = new ProtocolTreeEditPart();
		} else if (model instanceof DnsServer) {
			part = new DnsServerTreeEditPart();
		} else if (model instanceof Interface) {
			part = new InterfaceTreeEditPart();
		} else if (model instanceof DnsServers) {
			part = new DnsServersTreeEditPart();
		} else if (model instanceof Interfaces) {
			part = new InterfacesTreeEditPart();
		} else if (model instanceof Subnet) {
			part = new SubnetTreeEditPart();
		}

		if (part != null) {
			part.setModel(model);
		}
		return part;
	}

}

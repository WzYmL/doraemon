/**
 * @file HelloTreeEditPart.java
 *
 * @author salever@126.com
 * 
 * @version 
 * 
 * @Create on 2009-7-14  ����03:25:47
 * 
 * @description 
 * 
 */
package org.salever.rcp.tools.topology_viewer.parts.tree;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPolicy;
import org.salever.rcp.tools.topology_viewer.models.DnsServers;
import org.salever.rcp.tools.topology_viewer.models.Interfaces;
import org.salever.rcp.tools.topology_viewer.models.Router;
import org.salever.rcp.tools.topology_viewer.models.TopoModel;
import org.salever.rcp.tools.topology_viewer.policies.CustomComponentEditPolicy;
import org.salever.rcp.tools.topology_viewer.system.ImagesContext;


/**
 * @author salever@126.com
 * 
 * @name HelloTreeEditPart
 * 
 */
public class RouterTreeEditPart extends TreeEditPart {

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE,
				new CustomComponentEditPolicy());
	}

	@Override
	@SuppressWarnings("unchecked")
	public List getModelChildren() {
		Router model = (Router) getModel();
		List<Object> list = new ArrayList<Object>();
		list.add(model.getProtocol());

		DnsServers dns = new DnsServers();
		dns.setChildren(model.getDnsServers());
		list.add(dns);

		Interfaces ins = new Interfaces();
		ins.setChildren(model.getInterfaces());
		list.add(ins);

		return list;
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if (event.getPropertyName().equals(TopoModel.P_NAME)) {
			refreshVisuals();
		} else if (event.getPropertyName().equals(Router.P_CHILDREN)) {
			refreshChildren();
		}
	}

	@Override
	protected void refreshVisuals() {
		Router model = (Router) getModel();
		setWidgetText(model.getName());
		setWidgetImage(ImagesContext.getImage(ImagesContext.OUT_LINE_ROUTER));
	}

}

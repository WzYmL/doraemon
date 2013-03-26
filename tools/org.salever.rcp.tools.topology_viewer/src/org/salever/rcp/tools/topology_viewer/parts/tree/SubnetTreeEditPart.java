/*******************************************************************************
 * Copyright (c) 2003, 2010 salever@126.com All rights reserved. 
 * 
 *
 * @author salever@126.com
 * @created 2010-5-4 下午05:47:32
 * @version 
 * @description 
 *******************************************************************************/
package org.salever.rcp.tools.topology_viewer.parts.tree;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPolicy;
import org.salever.rcp.tools.topology_viewer.models.Subnet;
import org.salever.rcp.tools.topology_viewer.models.TopoModel;
import org.salever.rcp.tools.topology_viewer.policies.CustomComponentEditPolicy;
import org.salever.rcp.tools.topology_viewer.system.ImagesContext;


/**
 * @description
 * 
 * @author salever@126.com
 * 
 */
public class SubnetTreeEditPart extends TreeEditPart {

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE,
				new CustomComponentEditPolicy());
	}

	@Override
	@SuppressWarnings("unchecked")
	public List getModelChildren() {
		Subnet model = (Subnet) getModel();
		List<Object> list = new ArrayList<Object>();
		list.addAll(model.getRouters());
		if (model.getSubnets().size() > 0) {
			list.addAll(model.getSubnets());
		}
		return list;
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if (event.getPropertyName().equals(TopoModel.P_NAME)) {
			refreshVisuals();
		} else if (event.getPropertyName().equals(Subnet.P_ROUERS)) {
			refreshChildren();
		} else if (event.getPropertyName().equals(Subnet.P_SUBNETS)) {
			refreshChildren();
		}
	}

	@Override
	protected void refreshVisuals() {
		Subnet model = (Subnet) getModel();
		setWidgetText(model.getName());
		setWidgetImage(ImagesContext.getImage(ImagesContext.OUT_LINE_SUBNET));
	}
}

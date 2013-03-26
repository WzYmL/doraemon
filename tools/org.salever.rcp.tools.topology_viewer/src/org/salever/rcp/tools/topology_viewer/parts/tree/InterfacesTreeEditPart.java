/*******************************************************************************
 * Copyright (c) 2003, 2010 salever@126.com All rights reserved. 
 * 
 *
 * @author salever@126.com
 * @created 2010-5-4 下午03:35:18
 * @version 
 * @description 
 *******************************************************************************/
package org.salever.rcp.tools.topology_viewer.parts.tree;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.swt.graphics.Image;
import org.salever.rcp.tools.topology_viewer.models.DnsServers;
import org.salever.rcp.tools.topology_viewer.models.Interfaces;
import org.salever.rcp.tools.topology_viewer.system.ImagesContext;


/**
 * @description
 * 
 * @author salever@126.com
 * 
 */
public class InterfacesTreeEditPart extends TreeEditPart {

	private Image image = ImagesContext
			.getImage(ImagesContext.OUT_LINE_INTERFACE);

	@SuppressWarnings("unchecked")
	@Override
	protected List getModelChildren() {
		Interfaces model = (Interfaces) getModel();
		return model.getChildren();
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		if (arg0.getPropertyName().equals(DnsServers.P_CHILD)) {
			refreshChildren();
		}
	}

	@Override
	protected void refreshVisuals() {
		setWidgetText("Interfaces");
		setWidgetImage(image);
	}
}

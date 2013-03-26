/*******************************************************************************
 * Copyright (c) 2003, 2010 salever@126.com All rights reserved. 
 * 
 *
 * @author salever@126.com
 * @created 2010-5-4 下午02:38:54
 * @version 
 * @description 
 *******************************************************************************/
package org.salever.rcp.tools.topology_viewer.parts.tree;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;
import org.salever.rcp.tools.topology_viewer.models.RoutingProtocol;
import org.salever.rcp.tools.topology_viewer.system.ImagesContext;


/**
 * @description
 * 
 * @author salever@126.com
 * 
 */
public class ProtocolTreeEditPart extends TreeEditPart {

	private Image image = ImagesContext
			.getImage(ImagesContext.OUT_LINE_PROTOCOL);

	@Override
	protected void refreshVisuals() {
		setWidgetText("routing_protocol");
		setWidgetImage(image);
	}

	@Override
	public void setWidget(Widget widget) {
		super.setWidget(widget);
		RoutingProtocol model = (RoutingProtocol) getModel();
		TreeItem item = (TreeItem) widget;
		if(item == null){
			return;
		}
		TreeItem child;
		child = new TreeItem(item, SWT.NONE);
		child.setText("exterior_gateway_protocol: "
				+ model.getExteriorGatwayProtocol());
		child.setImage(image);
		child = new TreeItem(item, SWT.NONE);
		child.setText("interior_gateway_protocol: "
				+ model.getInteriorGatewayProtocol());
		child.setImage(image);
	}
}

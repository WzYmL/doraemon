/*******************************************************************************
 * Copyright (c) 2003, 2010 salever@126.com All rights reserved. 
 * 
 *
 * @author salever@126.com
 * @created 2010-5-4 下午03:56:07
 * @version 
 * @description 
 *******************************************************************************/
package org.salever.rcp.tools.topology_viewer.parts.tree;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;
import org.salever.rcp.tools.topology_viewer.models.Interface;
import org.salever.rcp.tools.topology_viewer.system.ImagesContext;


/**
 * @description
 * 
 * @author salever@126.com
 * 
 */
public class InterfaceTreeEditPart extends TreeEditPart {

	private Image image = ImagesContext
			.getImage(ImagesContext.OUT_LINE_INTERFACE);

	@Override
	protected void refreshVisuals() {
		setWidgetText("interface");
		setWidgetImage(image);
	}

	@Override
	public void setWidget(Widget widget) {
		super.setWidget(widget);
		Interface model = (Interface) getModel();
		TreeItem item = (TreeItem) widget;
		if(item == null){
			return;
		}
		TreeItem child;
		child = new TreeItem(item, SWT.NONE);
		child.setText("ip: " + model.getIpAddress());
		child.setImage(image);
		child = new TreeItem(item, SWT.NONE);
		child.setText("name: " + model.getName());
		child.setImage(image);
		child = new TreeItem(item, SWT.NONE);
		child.setText("subnet_mask: " + model.getSubnetMask());
		child.setImage(image);
	}
}

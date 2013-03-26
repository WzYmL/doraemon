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

import org.eclipse.swt.graphics.Image;
import org.salever.rcp.tools.topology_viewer.models.DnsServer;
import org.salever.rcp.tools.topology_viewer.system.ImagesContext;


/**
 * @description
 * 
 * @author salever@126.com
 * 
 */
public class DnsServerTreeEditPart extends TreeEditPart {

	private Image image = ImagesContext.getImage(ImagesContext.OUT_LINE_DNS);

	@Override
	protected void refreshVisuals() {
		setWidgetText("DNS Server Ip: "
				+ ((DnsServer) getModel()).getIpAddress());
		setWidgetImage(image);
	}

}

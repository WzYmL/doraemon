/*******************************************************************************
 * Copyright (c) 2003, 2010 salever@126.com All rights reserved. 
 * 
 *
 * @author salever@126.com
 * @created 2010-5-5 上午11:54:12
 * @version 
 * @description 
 *******************************************************************************/
package org.salever.rcp.tools.topology_viewer.figures;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.graphics.Image;
import org.salever.rcp.tools.topology_viewer.system.ImagesContext;


/**
 * @description
 * 
 * @author salever@126.com
 * 
 */
public interface FigureConstants {

	Image DEFAULT_ROUTER_IMAGE = ImagesContext.getImage(ImagesContext.MODEL_ROUTER);

	Image DEFAULT_SUBNET_IMAGE = ImagesContext
			.getImage(ImagesContext.MODEL_SUBNET);

	Dimension DEFAULT_ROUETR_SIZE = new Dimension(50, 85);

	Dimension DEFAULT_SUBNET_SIZE = new Dimension(60, 100);

}

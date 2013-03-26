/*******************************************************************************
 * Copyright (c) 2003, 2010 salever@126.com All rights reserved. 
 * 
 *
 * @author salever@126.com
 * @created 2010-5-5 下午04:27:40
 * @version 
 * @description 
 *******************************************************************************/
package org.salever.rcp.tools.topology_viewer.figures;

import org.salever.rcp.tools.topology_viewer.models.TopoModel;

/**
 * @description
 * 
 * @author salever@126.com
 * 
 */
public class SubnetFigure extends TopoFigure {

	public SubnetFigure(TopoModel model) {
		super(model);
		setImage(FigureConstants.DEFAULT_SUBNET_IMAGE);
		setPreferSize(FigureConstants.DEFAULT_SUBNET_SIZE);
		setName(model.getName());
	}
}

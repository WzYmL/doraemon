/**
 * 
 */
package org.salever.rcp.tools.topology_viewer.figures;

import org.salever.rcp.tools.topology_viewer.models.TopoModel;

/**
 * @author salever@126.com
 * 
 */
public class RouterFigure extends TopoFigure {

	public RouterFigure(TopoModel model) {
		super(model);
		setImage(FigureConstants.DEFAULT_ROUTER_IMAGE);
		setPreferSize(FigureConstants.DEFAULT_ROUETR_SIZE);
		setName(model.getName());
	}

}

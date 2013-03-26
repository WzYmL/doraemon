/*******************************************************************************
 * Copyright (c) 2003, 2010 salever@126.com All rights reserved. 
 * 
 *
 * @author salever@126.com
 * @created 2010-5-7 下午03:29:17
 * @version 
 * @description 
 *******************************************************************************/
package org.salever.rcp.tools.topology_viewer.figures;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.swt.SWT;

/**
 * @description 
 *
 * @author salever@126.com
 *
 */
public class ConnectionFigure extends PolylineConnection{

	private PolygonDecoration sourceDecoration;
	private PolygonDecoration targetDecoration;
	
	public ConnectionFigure() {
		super();
		sourceDecoration = new PolygonDecoration();
		targetDecoration = new PolygonDecoration();
		this.setSourceDecoration(sourceDecoration);
		this.setTargetDecoration(targetDecoration);
	}
	
	@Override
	public void paint(Graphics graphics) {
		graphics.setAntialias(SWT.ON);
		super.paint(graphics);
	}
}

/*******************************************************************************
 * Copyright (c) 2003, 2010 salever@126.com All rights reserved. 
 * 
 *
 * @author salever@126.com
 * @created 2010-5-5 下午04:37:42
 * @version 
 * @description 
 *******************************************************************************/
package org.salever.rcp.tools.topology_viewer.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.swt.graphics.Image;
import org.salever.rcp.tools.topology_viewer.models.TopoModel;


/**
 * @description
 * 
 * @author salever@126.com
 * 
 */
public abstract class TopoFigure extends Figure implements HandleBounds {

	private Label nodeIcon; // 节点图片描述
	private WrapLabel nodeDesc; // 节点文本描述

	protected Image image;
	protected Dimension preferSize;
	private TooltipFigure tooltip;

	public TopoFigure(TopoModel model) {

		ToolbarLayout layout = new ToolbarLayout();
		layout.setStretchMinorAxis(false);
		layout.setMinorAlignment(ToolbarLayout.ALIGN_CENTER);
		layout.setSpacing(2);

		this.setLayoutManager(layout);
		this.add(nodeIcon = new Label());
		this.add(nodeDesc = new WrapLabel());

		Point location = model.getConstraint().getLocation();
		this.setLocation(location);
		tooltip = new TooltipFigure();
		this.setToolTip(tooltip);
	}

	@Override
	public boolean containsPoint(int x, int y) {
		return nodeIcon.containsPoint(x, y);
	}

	@Override
	public Rectangle getHandleBounds() {
		return nodeIcon.getBounds();
	}

	public Label getImage() {
		return nodeIcon;
	}

	public String getName() {
		return nodeDesc.getText();
	}

	public void setImage(Image image) {
		this.image = image;
		nodeIcon.setIcon(image);
		nodeIcon.setBounds(new Rectangle(image.getBounds()));
	}

	public void setName(String text) {
		nodeDesc.setText(text);
		tooltip.setText(text);
	}

	public void setPreferSize(Dimension preferSize) {
		this.preferSize = preferSize;
		this.setSize(preferSize);
	}
}

/*******************************************************************************
 * Copyright (c) 2000, 2010 salever. All rights reserved. 
 *
 *******************************************************************************/
package org.salever.gef.examples.layersupport.shapes.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.LayeredPane;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.swt.graphics.Image;
import org.salever.gef.examples.layersupport.shapes.ShapesPlugin;
import org.salever.gef.examples.layersupport.shapes.model.Container;

/**
 * @author Salever
 *
 */
public class ContainerFigure extends Figure implements ILayerSupport{

	private final static Image seaImage = ShapesPlugin.createImage("icons/bg.jpg");
	private final static Image skyImage = ShapesPlugin.createImage("icons/bg1.gif");
	
	private LayeredPane layeredPane;
	private Layer backgroundLayer, foreGroundLayer;
	private ImageFigure bgImage, fgImage;
	
	private Container model;
	/**
	 * 
	 */
	public ContainerFigure(Container model) {
		super();
		this.model = model;
		
		this.setLayoutManager(new StackLayout());
		LineBorder border = new LineBorder();
		border.setWidth(1);
		border.setColor(ColorConstants.black);
		setBorder(border);
	
		layeredPane = new LayeredPane();
		this.add(layeredPane);
		
		backgroundLayer = new FreeformLayer();
		backgroundLayer.setOpaque(false);
		backgroundLayer.setBackgroundColor(ColorConstants.gray);
		backgroundLayer.setLayoutManager(new StackLayout());
		backgroundLayer.setBorder(new LineBorder());
		
		bgImage = new ImageFigure();
		bgImage.setImage(seaImage);
		backgroundLayer.add(bgImage);	
		backgroundLayer.add(new Label("BackGound Layer"));
		layeredPane.add(backgroundLayer,LayerID.BACKGOROUND_LAYER);
		
		foreGroundLayer = new FreeformLayer();
		foreGroundLayer.setLayoutManager(new StackLayout());		
		
		layeredPane.addLayerAfter(foreGroundLayer,LayerID.DEFAULT,LayerID.FOREGROUND_LAYER);	
				
		fgImage = new ImageFigure(skyImage);
		//foreGroundLayer.add(fgImage);
		this.setLocation(model.getLocation());
		this.setPreferredSize(model.getSize());
		
	
		add(fgImage);
		add(new Label("Default Layer"));
		
		//foreGroundLayer.add(new Label("Foreground Layer"));
		
	}
	/* (non-Javadoc)
	 * @see org.salever.gef.examples.shapes.figures.ILayerSupport#getLayer(int)
	 */
	@Override
	public Layer getLayer(int id) {
		return layeredPane.getLayer(id);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.draw2d.Figure#paintFigure(org.eclipse.draw2d.Graphics)
	 */
	@Override
	protected void paintFigure(Graphics graphics) {
		super.paintFigure(graphics);
		
		
	}
}

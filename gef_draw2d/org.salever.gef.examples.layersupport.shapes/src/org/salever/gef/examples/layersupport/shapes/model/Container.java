/*******************************************************************************
 * Copyright (c) 2000, 2010 salever. All rights reserved. 
 *
 *******************************************************************************/
package org.salever.gef.examples.layersupport.shapes.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Image;

/**
 * @author LiXiaopeng
 *
 */
public class Container extends Shape{

	public static final String P_CHILD_ADD = "Container.ChildAdd";
	public static final String P_CHILD_REMOVE = "Container.ChildRemove";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Shape> children;
	
	
	/**
	 * 
	 */
	public Container() {
		super();
		children = new ArrayList<Shape>();
	}
	
	public void addChild(Shape s){
		if(!children.contains(s)){
			children.add(s);
			firePropertyChange(P_CHILD_ADD, null, s);
		}
	}
	
	public void removeChild(Shape s){
		if(children.contains(s)){
			children.remove(s);
			firePropertyChange(P_CHILD_REMOVE, s, null);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.examples.shapes.model.Shape#getIcon()
	 */
	@Override
	public Image getIcon() {
		return null;
	}

	
	
}

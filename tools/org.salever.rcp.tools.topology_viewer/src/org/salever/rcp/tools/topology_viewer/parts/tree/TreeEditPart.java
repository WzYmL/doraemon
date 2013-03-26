/**
 * @file CustomTreeEditPart.java
 *
 * @author salever@126.com
 * 
 * @version 
 * 
 * @Create on 2009-7-14  ����03:20:19
 * 
 * @description 
 * 
 */
package org.salever.rcp.tools.topology_viewer.parts.tree;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.gef.editparts.AbstractTreeEditPart;
import org.salever.rcp.tools.topology_viewer.models.AbstractModel;


/**
 * @author salever@126.com
 * 
 * @name CustomTreeEditPart
 * 
 */
public abstract class TreeEditPart extends AbstractTreeEditPart implements
		PropertyChangeListener {

	@Override
	public void activate() {
		super.activate();
		((AbstractModel) getModel()).addPropertyChangeListener(this);
	}

	@Override
	public void deactivate() {
		super.deactivate();
		((AbstractModel) getModel()).removePropertyChangeListener(this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {

	}

}

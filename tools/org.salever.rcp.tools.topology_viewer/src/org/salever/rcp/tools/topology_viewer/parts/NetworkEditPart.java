/**
 * @file ContentsEditPart.java
 *
 * @author salever@126.com
 * 
 * @version 
 * 
 * @Create on 2009-7-13  ����02:45:21
 * 
 * @description 
 * 
 */
package org.salever.rcp.tools.topology_viewer.parts;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.gef.EditPolicy;
import org.salever.rcp.tools.topology_viewer.models.Network;
import org.salever.rcp.tools.topology_viewer.policies.CustomXYLayoutEditPolicy;


/**
 * @author salever@126.com
 * 
 * @name ContentsEditPart
 * 
 */
public class NetworkEditPart extends EditPartWithListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.LAYOUT_ROLE,
				new CustomXYLayoutEditPolicy());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	@Override
	protected IFigure createFigure() {
		FreeformLayer figure = new FreeformLayer();
		figure.setLayoutManager(new XYLayout());
		return figure;
	}

	@Override
	@SuppressWarnings("unchecked")
	protected List getModelChildren() {
		return ((Network) getModel()).getChildren();
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		if (arg0.getPropertyName().equals(Network.P_CHILDREN)) {
			refreshChildren();
		}
	}
}

/**
 * @file CustomAbstractLineConnectionEditPart.java
 *
 * @author salever@126.com
 * 
 * @version 
 * 
 * @Create on 2009-7-14  ����10:36:01
 * 
 * @description 
 * 
 */
package org.salever.rcp.tools.topology_viewer.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.swt.SWT;
import org.salever.rcp.tools.topology_viewer.models.AbstractConnection;
import org.salever.rcp.tools.topology_viewer.policies.CustomConnectionEditPolicy;
import org.salever.rcp.tools.topology_viewer.policies.CustomConnectionEndPointEditPolicy;


/**
 * @author salever@126.com
 * 
 * @name CustomAbstractLineConnectionEditPart
 * 
 */
public class CustomConnectionEditPart extends
		AbstractConnectionEditPart implements PropertyChangeListener {

	@Override
	public void activate() {
		super.activate();
		((AbstractConnection) getModel()).addPropertyChangeListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub
		installEditPolicy(EditPolicy.CONNECTION_ROLE,
				new CustomConnectionEditPolicy());
		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE,
				new CustomConnectionEndPointEditPolicy());
		/*
		 * installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE, new
		 * CustomBendpointEditPolicy());
		 */
	}

	@Override
	protected IFigure createFigure() {
		PolylineConnection connection = new PolylineConnection();
		connection.setConnectionRouter(new BendpointConnectionRouter());
		return connection;
	}

	@Override
	public void deactivate() {
		((AbstractConnection) getModel()).removePropertyChangeListener(this);
		super.deactivate();
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getPropertyName().equals(AbstractConnection.P_BEND_POINT)) {
			refreshBendpoints();
		}
	}

	private void refreshBendpoints() {
		// TODO Auto-generated method stub
		List<Object> bendpoints = ((AbstractConnection) getModel())
				.getBendpoint();
		List<Object> constraint = new ArrayList<Object>();

		for (int i = 0; i < bendpoints.size(); ++i) {
			constraint.add(new AbsoluteBendpoint((Point) bendpoints.get(i)));
		}

		getConnectionFigure().setRoutingConstraint(constraint);

	}

	@Override
	protected void refreshVisuals() {
		refreshBendpoints();
		ConnectionLayer layer = (ConnectionLayer) getLayer(CONNECTION_LAYER);
		layer.setAntialias(SWT.ON);
		// layer.setConnectionRouter(new ManhattanConnectionRouter());
	}

}

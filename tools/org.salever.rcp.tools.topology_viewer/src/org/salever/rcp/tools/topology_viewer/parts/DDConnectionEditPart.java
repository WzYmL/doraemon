/**
 * @file LineConnectionEditPart.java
 *
 * @author salever@126.com
 * 
 * @version 
 * 
 * @Create on 2009-7-14  ����09:24:27
 * 
 * @description 
 * 
 */
package org.salever.rcp.tools.topology_viewer.parts;

import java.beans.PropertyChangeEvent;

import org.eclipse.draw2d.IFigure;
import org.salever.rcp.tools.topology_viewer.figures.ConnectionFigure;
import org.salever.rcp.tools.topology_viewer.models.DoubleArrowConnection;


/**
 * @author salever@126.com
 * 
 * @name LineConnectionEditPart
 * 
 */
public class DDConnectionEditPart extends
		CustomConnectionEditPart {

	@Override
	protected IFigure createFigure() {
		ConnectionFigure figure = new ConnectionFigure();
		return figure;
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		if (arg0.getPropertyName().equals(
				DoubleArrowConnection.LINE_WIDTH_PROP)) {
			((DoubleArrowConnection) getModel()).setLineWidth(Integer
					.parseInt(arg0.getNewValue().toString()));
		}
		super.propertyChange(arg0);
	}
}

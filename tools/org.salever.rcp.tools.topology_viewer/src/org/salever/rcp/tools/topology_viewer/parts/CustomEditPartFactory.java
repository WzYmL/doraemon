/**
 * @file HelloPartFactory.java
 *
 * @author salever@126.com
 * 
 * @version 
 * 
 * @Create on 2009-7-13  ����02:27:34
 * 
 * @description 
 * 
 */
package org.salever.rcp.tools.topology_viewer.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.salever.rcp.tools.topology_viewer.models.DoubleArrowConnection;
import org.salever.rcp.tools.topology_viewer.models.Network;
import org.salever.rcp.tools.topology_viewer.models.Router;
import org.salever.rcp.tools.topology_viewer.models.Subnet;


/**
 * @author salever@126.com
 * 
 * @name HelloPartFactory
 * 
 */
public class CustomEditPartFactory implements EditPartFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart,
	 * java.lang.Object)
	 */
	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		// TODO Auto-generated method stub

		EditPart part = getEditPartForElement(model);
		part.setModel(model);
		return part;
	}

	/**
	 * 
	 */
	private EditPart getEditPartForElement(Object element) {
		if (element instanceof Router) {
			return new RouterEditPart();
		} else if (element instanceof Network) {
			return new NetworkEditPart();
		} else if (element instanceof DoubleArrowConnection) {
			return new DDConnectionEditPart();
		} else if (element instanceof Subnet) {
			return new SubnetEditPart();
		}
		throw new RuntimeException("cast error");
	}
}

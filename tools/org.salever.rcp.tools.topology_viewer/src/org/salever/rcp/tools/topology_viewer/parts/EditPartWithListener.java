/**
 * @file EditPartWithListener.java
 *
 * @author salever@126.com
 * 
 * @version 
 * 
 * @Create on 2009-7-13  ����04:05:01
 * 
 * @description 
 * 
 */
package org.salever.rcp.tools.topology_viewer.parts;

import java.beans.PropertyChangeListener;

import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.salever.rcp.tools.topology_viewer.models.AbstractModel;


/**
 * @author salever@126.com
 * 
 * @name EditPartWithListener
 * 
 */
public abstract class EditPartWithListener extends AbstractGraphicalEditPart
		implements PropertyChangeListener {

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

}

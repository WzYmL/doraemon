/**
 * @file CustomDirectEditPolicy.java
 *
 * @author salever@126.com
 * 
 * @version 
 * 
 * @Create on 2009-7-13  ����07:30:25
 * 
 * @description 
 * 
 */
package org.salever.rcp.tools.topology_viewer.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.salever.rcp.tools.topology_viewer.commands.DirectEditCommand;
import org.salever.rcp.tools.topology_viewer.models.TopoModel;


/**
 * @author salever@126.com
 * 
 * @name CustomDirectEditPolicy
 * 
 */
public class CustomDirectEditPolicy extends DirectEditPolicy {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.editpolicies.DirectEditPolicy#getDirectEditCommand(org
	 * .eclipse.gef.requests.DirectEditRequest)
	 */
	@Override
	protected Command getDirectEditCommand(DirectEditRequest request) {
		// TODO Auto-generated method stub

		DirectEditCommand command = new DirectEditCommand();
		command.setModel((TopoModel) (getHost().getModel()));
		command.setNewText((String) request.getCellEditor().getValue());
		return command;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.editpolicies.DirectEditPolicy#showCurrentEditValue(org
	 * .eclipse.gef.requests.DirectEditRequest)
	 */
	@Override
	protected void showCurrentEditValue(DirectEditRequest request) {
		// TODO Auto-generated method stub

	}

}

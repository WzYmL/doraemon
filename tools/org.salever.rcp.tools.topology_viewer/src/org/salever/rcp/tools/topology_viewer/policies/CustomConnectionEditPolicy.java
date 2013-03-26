/**
 * @file CustomConnectionEditPolicy.java
 *
 * @author salever@126.com
 * 
 * @version 
 * 
 * @Create on 2009-7-14  ����11:02:22
 * 
 * @description 
 * 
 */
package org.salever.rcp.tools.topology_viewer.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.requests.GroupRequest;
import org.salever.rcp.tools.topology_viewer.commands.DeleteConnectionCommand;


/**
 * @author salever@126.com
 * 
 * @name CustomConnectionEditPolicy
 * 
 */
public class CustomConnectionEditPolicy extends ConnectionEditPolicy {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.editpolicies.ConnectionEditPolicy#getDeleteCommand(org
	 * .eclipse.gef.requests.GroupRequest)
	 */
	@Override
	public Command getDeleteCommand(GroupRequest request) {
		DeleteConnectionCommand command = new DeleteConnectionCommand();
		command.setModel(getHost().getModel());
		return command;
	}
}

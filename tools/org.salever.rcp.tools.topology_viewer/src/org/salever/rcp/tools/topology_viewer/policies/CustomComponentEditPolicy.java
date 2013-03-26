/**
 * @file CustomComponentEdiPolicy.java
 *
 * @author salever@126.com
 * 
 * @version 
 * 
 * @Create on 2009-7-13  ����05:04:30
 * 
 * @description 
 * 
 */
package org.salever.rcp.tools.topology_viewer.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;
import org.salever.rcp.tools.topology_viewer.commands.DeleteCommand;


/**
 * @author salever@126.com
 * 
 * @name CustomComponentEdiPolicy
 * 
 */
public class CustomComponentEditPolicy extends ComponentEditPolicy {

	@Override
	protected Command createDeleteCommand(GroupRequest request) {
		DeleteCommand deleteCommand = new DeleteCommand();
		deleteCommand.setParent(getHost().getParent().getModel());
		deleteCommand.setChild(getHost().getModel());
		return deleteCommand;
	}
}

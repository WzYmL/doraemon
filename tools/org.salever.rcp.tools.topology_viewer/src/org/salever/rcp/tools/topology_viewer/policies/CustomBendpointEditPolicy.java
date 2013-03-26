/**
 * @file CustomBendpointEditPolicy.java
 *
 * @author salever@126.com
 * 
 * @version 
 * 
 * @Create on 2009-7-14  ����02:12:32
 * 
 * @description 
 * 
 */
package org.salever.rcp.tools.topology_viewer.policies;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.BendpointEditPolicy;
import org.eclipse.gef.requests.BendpointRequest;
import org.salever.rcp.tools.topology_viewer.commands.CreateBendpointCommand;
import org.salever.rcp.tools.topology_viewer.commands.DeleteBendpointCommand;
import org.salever.rcp.tools.topology_viewer.commands.MoveBendpointCommand;


/**
 * @author salever@126.com
 * 
 * @name CustomBendpointEditPolicy
 * 
 */
public class CustomBendpointEditPolicy extends BendpointEditPolicy {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.editpolicies.BendpointEditPolicy#getCreateBendpointCommand
	 * (org.eclipse.gef.requests.BendpointRequest)
	 */
	@Override
	protected Command getCreateBendpointCommand(BendpointRequest request) {
		// TODO Auto-generated method stub

		Point point = request.getLocation();
		getConnection().translateToRelative(point);

		CreateBendpointCommand command = new CreateBendpointCommand();
		command.setLocation(point);
		command.setConnection(getHost().getModel());
		command.setIndex(request.getIndex());
		return command;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.editpolicies.BendpointEditPolicy#getDeleteBendpointCommand
	 * (org.eclipse.gef.requests.BendpointRequest)
	 */
	@Override
	protected Command getDeleteBendpointCommand(BendpointRequest request) {
		// TODO Auto-generated method stub
		DeleteBendpointCommand command = new DeleteBendpointCommand();
		command.setConnection(getHost().getModel());
		command.setIndex(request.getIndex());
		return command;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.editpolicies.BendpointEditPolicy#getMoveBendpointCommand
	 * (org.eclipse.gef.requests.BendpointRequest)
	 */
	@Override
	protected Command getMoveBendpointCommand(BendpointRequest request) {
		// TODO Auto-generated method stub
		Point point = request.getLocation();
		getConnection().translateToRelative(point);

		MoveBendpointCommand command = new MoveBendpointCommand();
		command.setLocation(point);
		command.setConnection(getHost().getModel());
		command.setIndex(request.getIndex());
		return command;
	}

}

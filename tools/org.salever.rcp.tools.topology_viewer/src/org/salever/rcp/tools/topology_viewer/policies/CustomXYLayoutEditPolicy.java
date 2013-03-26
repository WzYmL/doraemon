/**
 * @file CustomXYLayoutEditPolicy.java
 *
 * @author salever@126.com
 * 
 * @version 
 * 
 * @Create on 2009-7-13  ����03:23:02
 * 
 * @description 
 * 
 */
package org.salever.rcp.tools.topology_viewer.policies;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.salever.rcp.tools.topology_viewer.commands.ChangeConstraintCommand;
import org.salever.rcp.tools.topology_viewer.commands.CreateCommand;
import org.salever.rcp.tools.topology_viewer.figures.FigureConstants;
import org.salever.rcp.tools.topology_viewer.models.Network;
import org.salever.rcp.tools.topology_viewer.models.Router;
import org.salever.rcp.tools.topology_viewer.models.Subnet;
import org.salever.rcp.tools.topology_viewer.models.TopoModel;


/**
 * @author salever@126.com
 * 
 * @name CustomXYLayoutEditPolicy
 * 
 */
public class CustomXYLayoutEditPolicy extends XYLayoutEditPolicy {

	@Override
	protected Command createAddCommand(EditPart child, Object constraint) {
		return null;
	}

	@Override
	protected Command createChangeConstraintCommand(EditPart child,
			Object constraint) {
		ChangeConstraintCommand command = new ChangeConstraintCommand();
		command.setModel((TopoModel) child.getModel());

		Rectangle rect = (Rectangle) constraint;
		if (rect.x < 0) {
			rect.x = 0;
		}
		if (rect.y < 0) {
			rect.y = 0;
		}
		command.setConstraint(rect);
		return command;
	}

	@Override
	protected EditPolicy createChildEditPolicy(EditPart child) {
		return new NonResizableEditPolicy();
	}

	@Override
	protected Command getCreateCommand(CreateRequest request) {

		CreateCommand createCommand = new CreateCommand();
		Rectangle constraint = (Rectangle) this.getConstraintFor(request);
		TopoModel model = (TopoModel) request.getNewObject();
		if(model instanceof Router){
			constraint.setSize(FigureConstants.DEFAULT_ROUETR_SIZE);
		}
		if(model instanceof Subnet){
			constraint.setSize(FigureConstants.DEFAULT_SUBNET_SIZE);
		}
		
		model.setConstraint(constraint);
		createCommand.setParent((Network) (getHost().getModel()));
		createCommand.setChild(model);
		return createCommand;
	}

	@Override
	protected Command getDeleteDependantCommand(Request request) {
		return null;
	}
}

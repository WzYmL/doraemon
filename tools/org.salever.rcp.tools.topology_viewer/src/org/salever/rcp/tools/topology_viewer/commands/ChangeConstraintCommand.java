/**
 * @file ChangeConstraintCommand.java
 *
 * @author salever@126.com
 * 
 * @version 
 * 
 * @Create on 2009-7-13  ����03:37:31
 * 
 * @description 
 * 
 */
package org.salever.rcp.tools.topology_viewer.commands;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.salever.rcp.tools.topology_viewer.models.TopoModel;


/**
 * @author salever@126.com
 * 
 * @name ChangeConstraintCommand
 * 
 */
public class ChangeConstraintCommand extends Command {

	private TopoModel model;

	private Rectangle constraint;

	private Rectangle oldConstraint;

	@Override
	public void execute() {
		model.setConstraint(constraint);
	}

	/**
	 * @param constraint
	 *            the constraint to set
	 */
	public void setConstraint(Object constraint) {
		this.constraint = (Rectangle) constraint;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(TopoModel model) {
		this.model = model;
		this.oldConstraint = model.getConstraint();
	}

	@Override
	public void undo() {
		model.setConstraint(oldConstraint);
	}

}

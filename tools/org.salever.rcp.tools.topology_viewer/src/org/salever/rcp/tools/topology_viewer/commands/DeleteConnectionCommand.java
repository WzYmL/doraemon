/**
 * @file DeleteConnectionCommand.java
 *
 * @author salever@126.com
 * 
 * @version 
 * 
 * @Create on 2009-7-14  ����10:54:04
 * 
 * @description 
 * 
 */
package org.salever.rcp.tools.topology_viewer.commands;

import org.eclipse.gef.commands.Command;
import org.salever.rcp.tools.topology_viewer.models.AbstractConnection;


/**
 * @author salever@126.com
 * 
 * @name DeleteConnectionCommand
 * 
 */
public class DeleteConnectionCommand extends Command {

	private AbstractConnection model;

	@Override
	public void execute() {
		model.detachSource();
		model.detachTarget();
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(Object model) {
		this.model = (AbstractConnection) model;
	}

	@Override
	public void undo() {
		model.attachSource();
		model.attachTarget();
	}
}

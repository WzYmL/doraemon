/**
 * @file CreateConnectionCommand.java
 *
 * @author salever@126.com
 * 
 * @version 
 * 
 * @Create on 2009-7-14  ����09:59:31
 * 
 * @description 
 * 
 */
package org.salever.rcp.tools.topology_viewer.commands;

import org.eclipse.gef.commands.Command;
import org.salever.rcp.tools.topology_viewer.models.AbstractConnection;
import org.salever.rcp.tools.topology_viewer.models.TopoModel;


/**
 * @author salever@126.com
 * 
 * @name CreateConnectionCommand
 * 
 */
public class CreateConnectionCommand extends Command {

	private TopoModel source, target;

	private AbstractConnection connection;

	@Override
	public boolean canExecute() {
		if ((source == null) || (target == null)) {
			return false;
		}

		if (source.equals(target)) {
			return false;
		}

		return true;
	}

	@Override
	public void execute() {
		connection.attachSource();
		connection.attachTarget();
	}

	public void setConnection(Object model) {
		connection = (AbstractConnection) model;
	}

	public void setSource(Object model) {
		source = (TopoModel) model;
		connection.setSource(source);
	}

	public void setTarget(Object model) {
		target = (TopoModel) model;
		connection.setTarget(target);
	}

	@Override
	public void undo() {
		connection.detachSource();
		connection.detachTarget();
	}
}

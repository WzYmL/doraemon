/**
 * @file ReconnectConnectionCommand.java
 *
 * @author salever@126.com
 * 
 * @version 
 * 
 * @Create on 2009-7-14  ����11:06:38
 * 
 * @description 
 * 
 */
package org.salever.rcp.tools.topology_viewer.commands;

import org.eclipse.gef.commands.Command;
import org.salever.rcp.tools.topology_viewer.models.AbstractConnection;
import org.salever.rcp.tools.topology_viewer.models.Router;


/**
 * @author salever@126.com
 * 
 * @name ReconnectConnectionCommand
 * 
 */
public class ReconnectConnectionCommand extends Command {

	private AbstractConnection connection;

	// private HelloModel source, target;

	@Override
	public void execute() {
		connection.attachSource();
		connection.attachTarget();
	}

	public void setConnectionModel(Object model2) {
		// TODO Auto-generated method stub
		connection = (AbstractConnection) model2;
	}

	public void setNewSource(Object model2) {
		// TODO Auto-generated method stub
		connection.setSource((Router) model2);
	}

	public void setNewTarget(Object model2) {
		// TODO Auto-generated method stub
		connection.setTarget((Router) model2);
	}

	@Override
	public void undo() {
		connection.detachSource();
		connection.detachTarget();
	}
}
/**
 * @file DeleteBendpointCommand.java
 *
 * @author salever@126.com
 * 
 * @version 
 * 
 * @Create on 2009-7-14  ����02:28:07
 * 
 * @description 
 * 
 */
package org.salever.rcp.tools.topology_viewer.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.salever.rcp.tools.topology_viewer.models.AbstractConnection;


/**
 * @author salever@126.com
 * 
 * @name DeleteBendpointCommand
 * 
 */
public class DeleteBendpointCommand extends Command {

	private AbstractConnection connection;

	private Point oldlocation;

	private int index;

	@Override
	public void execute() {
		oldlocation = (Point) connection.getBendpoint().get(index);
		connection.removeBendpoint(index);
	}

	/**
	 * @param connection
	 *            the connection to set
	 */
	public void setConnection(Object connection) {
		this.connection = (AbstractConnection) connection;
	}

	/**
	 * @param index
	 *            the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(Object location) {
		this.oldlocation = (Point) location;
	}

	@Override
	public void undo() {
		connection.addBendpoint(index, oldlocation);
	}
}

/**
 * @file CreateBendpointCommand.java
 *
 * @author salever@126.com
 * 
 * @version 
 * 
 * @Create on 2009-7-14  ����02:16:05
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
 * @name CreateBendpointCommand
 * 
 */
public class CreateBendpointCommand extends Command {

	private AbstractConnection connection;

	private Point location;

	private int index;

	@Override
	public void execute() {
		connection.addBendpoint(index, location);
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
		this.location = (Point) location;
	}

	@Override
	public void undo() {
		connection.removeBendpoint(index);
	}

}

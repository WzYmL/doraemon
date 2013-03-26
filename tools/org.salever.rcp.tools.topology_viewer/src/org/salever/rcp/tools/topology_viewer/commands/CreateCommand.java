/**
 * @file CreateCommand.java
 *
 * @author salever@126.com
 * 
 * @version 
 * 
 * @Create on 2009-7-13  ����05:39:25
 * 
 * @description 
 * 
 */
package org.salever.rcp.tools.topology_viewer.commands;

import org.eclipse.gef.commands.Command;
import org.salever.rcp.tools.topology_viewer.models.Network;
import org.salever.rcp.tools.topology_viewer.models.TopoModel;


/**
 * @author salever@126.com
 * 
 * @name CreateCommand
 * 
 */
public class CreateCommand extends Command {

	private Network parent;

	private TopoModel child;

	@Override
	public void execute() {
		parent.addChild(child);
	}

	/**
	 * @param child
	 *            the child to set
	 */
	public void setChild(TopoModel child) {
		this.child = child;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(Network parent) {
		this.parent = parent;
	}

	@Override
	public void undo() {
		parent.removeChild(child);
	}

}

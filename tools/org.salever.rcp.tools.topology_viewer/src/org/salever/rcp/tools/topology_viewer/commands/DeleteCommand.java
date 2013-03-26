/**
 * @file DeleteCommand.java
 *
 * @author salever@126.com
 * 
 * @version 
 * 
 * @Create on 2009-7-13  ����05:00:35
 * 
 * @description 
 * 
 */
package org.salever.rcp.tools.topology_viewer.commands;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.salever.rcp.tools.topology_viewer.models.AbstractConnection;
import org.salever.rcp.tools.topology_viewer.models.Network;
import org.salever.rcp.tools.topology_viewer.models.TopoModel;


/**
 * @author salever@126.com
 * 
 * @name DeleteCommand
 * 
 */
public class DeleteCommand extends Command {

	private Network parent;

	private TopoModel child;

	private List<Object> sourceConnections = new ArrayList<Object>();
	private List<Object> targetConnections = new ArrayList<Object>();

	@Override
	public void execute() {

		sourceConnections.addAll(child.getSourceConnection());
		targetConnections.addAll(child.getTargetConnection());

		for (int i = 0; i < sourceConnections.size(); ++i) {
			AbstractConnection model = (AbstractConnection) sourceConnections
					.get(i);
			model.detachSource();
			model.detachTarget();
		}

		for (int i = 0; i < targetConnections.size(); ++i) {
			AbstractConnection model = (AbstractConnection) targetConnections
					.get(i);
			model.detachSource();
			model.detachTarget();
		}

		parent.removeChild(child);
	}

	/**
	 * @param child
	 *            the child to set
	 */
	public void setChild(Object child) {
		this.child = (TopoModel) child;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(Object parent) {
		this.parent = (Network) parent;
	}

	@Override
	public void undo() {
		parent.addChild(child);

		for (int i = 0; i < sourceConnections.size(); ++i) {
			AbstractConnection model = (AbstractConnection) sourceConnections
					.get(i);
			model.attachSource();
			model.attachTarget();
		}

		for (int i = 0; i < targetConnections.size(); ++i) {
			AbstractConnection model = (AbstractConnection) targetConnections
					.get(i);
			model.attachSource();
			model.attachTarget();
		}

		sourceConnections.clear();
		targetConnections.clear();

	}
}

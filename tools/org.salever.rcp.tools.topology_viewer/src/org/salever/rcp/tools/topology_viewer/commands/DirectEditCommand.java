/**
 * @file DirectEditCommand.java
 *
 * @author salever@126.com
 * 
 * @version 
 * 
 * @Create on 2009-7-13  ����07:26:11
 * 
 * @description 
 * 
 */
package org.salever.rcp.tools.topology_viewer.commands;

import org.eclipse.gef.commands.Command;
import org.salever.rcp.tools.topology_viewer.models.TopoModel;


/**
 * @author salever@126.com
 * 
 * @name DirectEditCommand
 * 
 */
public class DirectEditCommand extends Command {

	private String oldText, newText;
	private TopoModel model;

	@Override
	public void execute() {
		oldText = model.getName();
		model.setName(newText);
	}

	/**
	 * @param model
	 *            the routerModel to set
	 */
	public void setModel(TopoModel model) {
		this.model = model;
	}

	/**
	 * @param newText
	 *            the newText to set
	 */
	public void setNewText(String newText) {
		this.newText = newText;
	}

	/**
	 * @param oldText
	 *            the oldText to set
	 */
	public void setOldText(String oldText) {
		this.oldText = oldText;
	}

	@Override
	public void undo() {
		model.setName(oldText);
	}
}

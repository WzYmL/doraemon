/**
 * 
 */
package org.salever.rcp.demo.action.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;

/**
 * @author LiXP
 * 
 */
public class OpenAction extends Action {
	public OpenAction() {
		super("&Save@Ctrl+S");
		this.setId("open");
	}

	@Override
	public void run() {
		MessageDialog.openInformation(null, "info", "Test");
	}
}

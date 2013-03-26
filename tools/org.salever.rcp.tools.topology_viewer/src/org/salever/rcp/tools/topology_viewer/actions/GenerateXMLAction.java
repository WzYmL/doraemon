/**
 * 
 */
package org.salever.rcp.tools.topology_viewer.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.salever.rcp.tools.topology_viewer.system.ImagesContext;
import org.salever.rcp.tools.topology_viewer.system.Messages;


/**
 * @author salever@126.com
 * 
 */
public class GenerateXMLAction extends Action {

	private final String ID = "com.vire.internal.topology_viewer.GenerateXMLAction";

	private final IWorkbenchWindow window;

	public GenerateXMLAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText(Messages.getString("Action.GenerateXML"));
		setImageDescriptor(ImagesContext.getImageDescriptor(ImagesContext.XML));
	}

	@Override
	public void run() {
		window.getActivePage();
		MessageDialog
				.openInformation(window.getShell(), "Infomation",
						"This menu item is reserved to generate XML file using another porgram.");
	}
}

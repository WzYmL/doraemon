/**
 * 
 */
package org.salever.rcp.tools.topology_viewer.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.salever.rcp.tools.topology_viewer.editors.DiagramEditor;
import org.salever.rcp.tools.topology_viewer.system.CustomFactory;
import org.salever.rcp.tools.topology_viewer.system.ImagesContext;
import org.salever.rcp.tools.topology_viewer.system.Messages;


/**
 * @author salever@126.com
 * 
 */
public class SwitchIconTypeAction extends Action {

	private final String ID = "com.vire.internal.topology_viewer.SwitchIconTypeAction";

	private final IWorkbenchWindow window;

	public SwitchIconTypeAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText(Messages.getString("Action.Switch_Icon"));
		setToolTipText(Messages.getString("Action.Switch_Icon_Tooltip"));
		setImageDescriptor(ImagesContext
				.getImageDescriptor(ImagesContext.SWITCH_ICON));
	}

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		IWorkbenchPage page = window.getActivePage();
		IEditorPart part = page.getActiveEditor();
		if (part instanceof DiagramEditor) {
			DiagramEditor diagramEditor = (DiagramEditor) part;
			diagramEditor.switchIconSize(CustomFactory.IconSize);
		}
	}
}

/**
 * 
 */
package org.salever.rcp.tools.topology_viewer.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;
import org.salever.rcp.tools.topology_viewer.system.ImagesContext;
import org.salever.rcp.tools.topology_viewer.system.Messages;


/**
 * @author salever@126.com
 * 
 */
public class ReloadAction extends Action {

	@SuppressWarnings("unused")
	private IWorkbenchWindow window;

	public ReloadAction(IWorkbenchWindow window) {
		this.window = window;
		setId("com.vire.internal.topology_viewer.ReloadAction");
		setText(Messages.getString("Action.Reload"));
		setToolTipText(Messages.getString("Action.Reload"));
		setHoverImageDescriptor(ImagesContext
				.getImageDescriptor(ImagesContext.RELOAD));
	}

	@Override
	public void run() {
		
	}
}

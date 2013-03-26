/**
 * 
 */
package org.salever.rcp.tools.topology_viewer.commands;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.salever.rcp.tools.topology_viewer.Activator;
import org.salever.rcp.tools.topology_viewer.editors.TextEditor;
import org.salever.rcp.tools.topology_viewer.editors.TextEditorInput;
import org.salever.rcp.tools.topology_viewer.models.Router;
import org.salever.rcp.tools.topology_viewer.system.CustomFactory;
import org.salever.rcp.tools.topology_viewer.system.Messages;


/**
 * @author salever@126.com
 * 
 */
public class OpenConfigureCommand extends Command {

	private Router router;

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		openConfigure();
	}

	/**
	 * @return the router
	 */
	public Router getRouter() {
		return router;
	}

	private void openConfigure() {

		String path = router.getConfigFile();

		if (path.equals("")) {
			if (!MessageDialog.openConfirm(Display.getCurrent()
					.getActiveShell(), Messages
					.getString("Message.Confirm_Title"), Messages
					.getString("Message.Confirm_Text"))) {
				return;
			}
			FileDialog dlg = new FileDialog(Display.getCurrent()
					.getActiveShell(), SWT.OPEN);
			dlg.setText(Messages.getString("Dialog.Select_Config_File"));
			dlg.setFilterExtensions(new String[] { "*.cfg", "*.*" });
			path = dlg.open();
			if (path != null) {
				router.setConfigFile(path);
				router.setInput(new TextEditorInput(path));
				CustomFactory.hasNewConfig = true;
			} else {
				return;
			}
		}
		IWorkbenchPage page = Activator.getDefault().getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		IEditorInput input = router.getInput();
		try {
			IEditorPart editor = page.findEditor(input);
			if (editor != null) {
				page.bringToTop(editor);
			} else {
				page.openEditor(input, TextEditor.ID);
			}

		} catch (PartInitException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param router
	 *            the router to set
	 */
	public void setRouter(Router router) {
		this.router = router;
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		super.undo();
	}

}

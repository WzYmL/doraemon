/**
 * 
 */
package org.salever.rcp.tools.wordcounts.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.salever.rcp.tools.wordcounts.Activator;
import org.salever.rcp.tools.wordcounts.editors.FileInput;
import org.salever.rcp.tools.wordcounts.editors.SampleTextEditor;

/**
 * @author
 * 
 */
public class OpenAction extends Action {

	private static final String ID = "org.salever.rcp.tech.chapter6.txtedit.action.open"; //$NON-NLS-1$

	private IWorkbenchWindow window;

	public OpenAction(IWorkbenchWindow window) {
		this.window = window;
		setText(Messages.OpenAction_1);
		setId(ID);
		setImageDescriptor(Activator.getImageDescriptor("icons/open.gif")); //$NON-NLS-1$
	}

	@Override
	public void run() {
		FileDialog dialog = new FileDialog(window.getShell(), SWT.OPEN);
		String path = dialog.open();
		if (path != null) {
			IWorkbenchPage page = window.getActivePage();
			FileInput input = new FileInput(path);
			try {
				page.openEditor(input, SampleTextEditor.ID);
			} catch (PartInitException e) {
				MessageDialog.openError(window.getShell(), "Error", //$NON-NLS-1$
						"Can not open ediior:" + path); //$NON-NLS-1$
			}
		}
	}
}

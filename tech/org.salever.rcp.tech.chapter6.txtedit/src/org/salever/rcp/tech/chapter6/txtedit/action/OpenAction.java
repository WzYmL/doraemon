/**
 * 
 */
package org.salever.rcp.tech.chapter6.txtedit.action;


import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.salever.rcp.tech.chapter6.txtedit.Activator;
import org.salever.rcp.tech.chapter6.txtedit.editor.FileInput;
import org.salever.rcp.tech.chapter6.txtedit.editor.SampleTextEditor;

/**
 * @author 
 *
 */
public class OpenAction extends Action{

	private static final String ID = "org.salever.rcp.tech.chapter6.txtedit.action.open";
	
	private IWorkbenchWindow window;
	
	public OpenAction(IWorkbenchWindow window) {
		this.window = window;
		setText("&Open File");
		setId(ID);
		setImageDescriptor(Activator.getImageDescriptor("icons/open.gif"));
	}
	
	@Override
	public void run() {
		FileDialog dialog = new FileDialog(window.getShell(), SWT.OPEN);
		String path = dialog.open();
		if(path != null){
			IWorkbenchPage page = window.getActivePage();
			FileInput input = new FileInput(path);
			try {
				page.openEditor(input, SampleTextEditor.ID);
			} catch (PartInitException e) {
				MessageDialog.openError(window.getShell(), "错误", "无法打开文件： " + path);
			}
		}
	}
}

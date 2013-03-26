/**
 * @file DiagramAction.java
 *
 * @author salever@126.com
 * 
 * @version 
 * 
 * @Create on 2009-7-13  上午11:20:18
 * 
 * @description 
 * 
 */
package org.salever.rcp.tools.topology_viewer.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.salever.rcp.tools.topology_viewer.editors.DiagramEditor;
import org.salever.rcp.tools.topology_viewer.editors.DiagramEditorInput;
import org.salever.rcp.tools.topology_viewer.models.Network;
import org.salever.rcp.tools.topology_viewer.system.CustomFactory;
import org.salever.rcp.tools.topology_viewer.system.ImagesContext;
import org.salever.rcp.tools.topology_viewer.system.Messages;
import org.salever.rcp.tools.topology_viewer.xml.util.XmlUtil;


/**
 * @author salever@126.com
 * 
 * @name DiiagramAction
 * 
 */
public class OpenAction extends Action {

	public static final String ID = "ccom.vire.internal.topology_viewer.OpenAction";

	private final IWorkbenchWindow window;

	private IWorkbenchPage page;

	public OpenAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText(Messages.getString("Action.Open_File"));
		setToolTipText(Messages.getString("Action.Open_File"));
		setImageDescriptor(ImagesContext.getImageDescriptor(ImagesContext.OPEN));
	}

	private String openFileDialog() {
		FileDialog dlg = new FileDialog(window.getShell(), SWT.OPEN);
		dlg.setText(Messages.getString("Dialog.Open_XML_File"));
		dlg.setFilterExtensions(new String[] { "*.xml" });
		return dlg.open();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	@Override
	public void run() {

		CustomFactory.setCurrentNewCount(1);

		String path = openFileDialog();
		
		page = window.getActivePage();

		if (path != null) {
			CustomFactory.setCurrentFilePath(path);
			if (page.getActiveEditor() != null) {
				page.closeAllEditors(true);

			}

			Network network = XmlUtil.generateNetwork(path);

			IEditorInput input = new DiagramEditorInput();
			((DiagramEditorInput) input).setNetwork(network);

			try {
				page.openEditor(input, DiagramEditor.ID);
			} catch (PartInitException e) {
				e.printStackTrace();
			}
			
			page.bringToTop(page.findEditor(input));
		}
	}

}

/*******************************************************************************
 * Copyright (c) 2003, 2010 salever@126.com All rights reserved. 
 * 
 *
 * @author salever@126.com
 * @created 2010-5-6 上午10:58:33
 * @version 
 * @description 
 *******************************************************************************/
package org.salever.rcp.tools.topology_viewer.actions;

import java.io.File;
import java.io.IOException;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.salever.rcp.tools.topology_viewer.editors.XmlEditor;
import org.salever.rcp.tools.topology_viewer.editors.XmlEditorInput;
import org.salever.rcp.tools.topology_viewer.system.CustomFactory;
import org.salever.rcp.tools.topology_viewer.system.ImagesContext;
import org.salever.rcp.tools.topology_viewer.system.Messages;


/**
 * @description
 * 
 * @author salever@126.com
 * 
 */
public class OpenMappingFileAction extends Action {

	private static final String ID = "com.vire.internal.topology_viewer.OpenMappingFileAction";

	IEditorInput input = new XmlEditorInput("");

	private IWorkbenchWindow window;

	public OpenMappingFileAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText(Messages.getString("Action.Open_Map"));
		setToolTipText(Messages.getString("Action.Open_Map"));
		setImageDescriptor(ImagesContext
				.getImageDescriptor(ImagesContext.OPEN_MAPPING_FILE));
	}

	@Override
	public void run() {

		String path = CustomFactory.getMappingFilePath();

		File file = new File(path);
		if (!file.exists()) {

			Shell shell = window.getShell();
			String title = Messages.getString("Dialog.Question");
			String message = Messages.getString("Dialog.Map_Message");
			String[] buttons = new String[] { "Import", "New", "Cancel" };
			MessageDialog md = new MessageDialog(shell, title, null, message,
					MessageDialog.QUESTION, buttons, 1);
			int result = md.open();
			if (result == 0) {
				FileDialog dlg = new FileDialog(Display.getCurrent()
						.getActiveShell(), SWT.OPEN);
				dlg.setText(Messages.getString("Dialog.Select_Map_File"));
				dlg.setFilterExtensions(new String[] { "*.cfg", "*.*" });
				path = dlg.open();
				if (path != null) {
					CustomFactory.setMappingFilePath(path);
					CustomFactory.hasNewConfig = true;
				} else {
					return;
				}
			} else if (result == 1) {
				FileDialog dlg = new FileDialog(Display.getCurrent()
						.getActiveShell(), SWT.SAVE);
				dlg.setText(Messages.getString("Dialog.New_Map_File"));
				dlg.setFilterExtensions(new String[] { "*.cfg", "*.*" });
				path = dlg.open();
				if (path != null) {
					File f = new File(path);
					try {
						f.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
					CustomFactory.setMappingFilePath(path);
					CustomFactory.hasNewConfig = true;
				} else {
					return;
				}

			} else {
				return;
			}
		}

		((XmlEditorInput) input).setPath(path);

		IWorkbenchPage page = window.getActivePage();

		try {
			if (page.findEditor(input) != null) {
				page.bringToTop(page.findEditor(input));
				return;
			}
			page.openEditor(input, XmlEditor.ID);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
}

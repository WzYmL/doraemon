/**
 * 
 */
package org.salever.rcp.tools.topology_viewer.actions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.salever.rcp.tools.topology_viewer.Activator;
import org.salever.rcp.tools.topology_viewer.editors.TextEditor;
import org.salever.rcp.tools.topology_viewer.editors.TextEditorInput;
import org.salever.rcp.tools.topology_viewer.models.Router;
import org.salever.rcp.tools.topology_viewer.parts.RouterEditPart;
import org.salever.rcp.tools.topology_viewer.system.CustomFactory;
import org.salever.rcp.tools.topology_viewer.system.ImagesContext;
import org.salever.rcp.tools.topology_viewer.system.Messages;


/**
 * @author salever@126.com
 * 
 */
public class OpenConfigureAction extends SelectionAction {
	public static final String ID = "com.vire.internal.topology_viewer.actions.OpenConfigureAction";

	public OpenConfigureAction(IWorkbenchPart part, int style) {
		super(part, style);
		setId(ID);
		setText(Messages.getString("Action.Open_Config"));
		setImageDescriptor(ImagesContext
				.getImageDescriptor(ImagesContext.OPEN_CONFIG_FILE));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#calculateEnabled()
	 */
	@Override
	protected boolean calculateEnabled() {
		// TODO Auto-generated method stub
		return hasSelected();
	}

	@SuppressWarnings("unchecked")
	private boolean hasSelected() {

		List<Object> objects = getSelectedObjects();
		if (objects.isEmpty()) {
			return false;
		}

		for (Iterator<Object> it = objects.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (!(obj instanceof RouterEditPart)) {
				return false;
			}
		}
		return true;
	}

	/*
	 * private Command createCommand(){ List<Object> objects =
	 * getSelectedObjects(); if(objects.isEmpty()){ return null; }
	 * for(Iterator<Object> it =objects.iterator(); it.hasNext(); ){ Object obj
	 * = it.next(); if(!(obj instanceof RouterEditPart)){ return null; } }
	 * 
	 * CompoundCommand compoundCmd = new
	 * CompoundCommand(GEFMessages.DeleteAction_ActionDeleteCommandName);
	 * for(int index = 0; index < objects.size(); ++index){ EditPart part =
	 * (EditPart)objects.get(index); OpenConfigureCommand cmd = new
	 * OpenConfigureCommand(); cmd.setRouter((RouterModel)part.getModel());
	 * compoundCmd.add(cmd); }
	 * 
	 * return compoundCmd; }
	 */

	private void openConfigure(Router router) {

		String path = router.getConfigFile();
		try {
			new BufferedReader(new InputStreamReader(new FileInputStream(path),
					"utf-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			Shell shell = Display.getCurrent().getActiveShell();
			String title = Messages.getString("Dialog.Question");
			String message = Messages.getString("Dialog.Message");
			String[] buttons = new String[] { "Import", "New", "Cancel" };
			MessageDialog md = new MessageDialog(shell, title, null, message,
					MessageDialog.QUESTION, buttons, 1);
			int result = md.open();
			if (result == 0) {
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
			} else if (result == 1) {
				FileDialog dlg = new FileDialog(Display.getCurrent()
						.getActiveShell(), SWT.SAVE);
				dlg.setText(Messages.getString("Dialog.New_Config_File"));
				dlg.setFileName(router.getName());
				dlg.setFilterExtensions(new String[] { "*.cfg", "*.*" });
				path = dlg.open();
				if (path != null) {
					File f = new File(path);
					try {
						f.createNewFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					router.setConfigFile(path);
					router.setInput(new TextEditorInput(path));
					CustomFactory.hasNewConfig = true;
				} else {
					return;
				}

			} else {
				return;
			}
			e1.printStackTrace();
		}
		/*
		 * if (path.equals("")) {
		 * 
		 * Shell shell = Display.getCurrent().getActiveShell(); String title =
		 * Messages.getString("Dialog.Question"); String message =
		 * Messages.getString("Dialog.Message"); String[] buttons = new
		 * String[]{"Import", "New", "Cancel"}; MessageDialog md = new
		 * MessageDialog(shell, title, null, message, MessageDialog.QUESTION
		 * ,buttons, 1); int result = md.open(); if(result == 0){ FileDialog dlg
		 * = new FileDialog(Display.getCurrent() .getActiveShell(), SWT.OPEN);
		 * dlg.setText(Messages.getString("Dialog.Select_Config_File"));
		 * dlg.setFilterExtensions(new String[] { "*.cfg", "*.*" }); path =
		 * dlg.open(); if (path != null) { router.setFilePath(path);
		 * router.setInput(new TextEditorInput(path));
		 * CustomFactory.hasNewConfig = true; } else { return; } }else if(result
		 * == 1){ FileDialog dlg = new FileDialog(Display.getCurrent()
		 * .getActiveShell(),SWT.SAVE);
		 * dlg.setText(Messages.getString("Dialog.New_Config_File"));
		 * dlg.setFileName(router.getName()); dlg.setFilterExtensions(new
		 * String[] { "*.cfg", "*.*" }); path = dlg.open(); if(path != null){
		 * File f = new File(path); try { f.createNewFile(); } catch
		 * (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } router.setFilePath(path); router.setInput(new
		 * TextEditorInput(path)); CustomFactory.hasNewConfig = true; }else{
		 * return; }
		 * 
		 * }else{ return; }
		 * 
		 * }
		 */
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

	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		// TODO Auto-generated method stub
		List<Object> objects = getSelectedObjects();
		if (objects.isEmpty()) {
			return;
		}

		for (Iterator<Object> it = objects.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (!(obj instanceof RouterEditPart)) {
				return;
			}
		}
		for (int index = 0; index < objects.size(); ++index) {
			EditPart part = (EditPart) objects.get(index);
			Router router = (Router) part.getModel();
			openConfigure(router);
		}

	}

}

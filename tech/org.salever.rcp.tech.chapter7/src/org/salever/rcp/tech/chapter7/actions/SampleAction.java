package org.salever.rcp.tech.chapter7.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

/**
 * Our sample action implements workbench action delegate.
 * The action proxy will be created by the workbench and
 * shown in the UI. When the user tries to use the action,
 * this delegate will be created and execution will be 
 * delegated to it.
 * @see IWorkbenchWindowActionDelegate
 */
public class SampleAction implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;
	/**
	 * The constructor.
	 */
	public SampleAction() {
	}

	/**
	 * The action has been activated. The argument of the
	 * method represents the 'real' action sitting
	 * in the workbench UI.
	 * @see IWorkbenchWindowActionDelegate#run
	 */
	public void run(IAction action) {
		// File standard dialog
		FileDialog fileDialog = new FileDialog(window.getShell());

		// Set the text
		fileDialog.setText("Select File");

		// Set filter on .txt files
		fileDialog.setFilterExtensions(new String[] { "*.txt" });

		// Put in a readable name for the filter
		fileDialog.setFilterNames(new String[] { "Textfiles(*.txt)" });

		// Open Dialog and save result of selection
		String selected = fileDialog.open();

		// Directly standard selection
		DirectoryDialog dirDialog = new DirectoryDialog(window.getShell());
		dirDialog.setText("Select your home directory");
		String selectedDir = dirDialog.open();

		// Select Font
		FontDialog fontDialog = new FontDialog(window.getShell());
		fontDialog.setText("Select your favorite font");
		FontData selectedFond = fontDialog.open();

		// Select Color
		ColorDialog colorDialog = new ColorDialog(window.getShell());
		fontDialog.setText("Select your favorite color");
		RGB selectedColor = colorDialog.open();

		// Now a few messages
		MessageDialog.openConfirm(window.getShell(), "Confirm",
				"Please confirm");
		MessageDialog.openError(window.getShell(), "Error", "Error occured");
		MessageDialog
				.openInformation(window.getShell(), "Info", "Info for you");
		MessageDialog.openQuestion(window.getShell(), "Question",
				"Really, really?");
		MessageDialog.openWarning(window.getShell(), "Warning", "I warn you");


	}

	/**
	 * Selection in the workbench has been changed. We 
	 * can change the state of the 'real' action here
	 * if we want, but this can only happen after 
	 * the delegate has been created.
	 * @see IWorkbenchWindowActionDelegate#selectionChanged
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

	/**
	 * We can use this method to dispose of any system
	 * resources we previously allocated.
	 * @see IWorkbenchWindowActionDelegate#dispose
	 */
	public void dispose() {
	}

	/**
	 * We will cache window object in order to
	 * be able to provide parent shell for the message dialog.
	 * @see IWorkbenchWindowActionDelegate#init
	 */
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}
}
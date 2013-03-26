package org.salever.rcp.tech.chapter9.actions;

import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;
import org.salever.rcp.tech.chapter9.Activator;

public class DeleteAction implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;

	@Override
	public void run(IAction action) {
		Preferences preferences = new ConfigurationScope()
				.getNode(Activator.PLUGIN_ID);
		Preferences sub1 = preferences.node("note1");
		Preferences sub2 = preferences.node("node2");

		// Show the values before deleting them
		MessageDialog.openInformation(window.getShell(), "Info",
				sub1.get("h1", "default"));

		MessageDialog.openInformation(window.getShell(), "Info",
				sub1.get("h2", "default"));

		MessageDialog.openInformation(window.getShell(), "Info",
				sub2.get("h1", "default"));

		// Delete the existing settings
		try {
			sub1.clear();
			sub2.clear();

		} catch (BackingStoreException e) {
			e.printStackTrace();

		}

		// Now show the values
		MessageDialog.openInformation(window.getShell(), "Info",
				sub1.get("h1", "default"));

		MessageDialog.openInformation(window.getShell(), "Info",
				sub1.get("h2", "default"));

		MessageDialog.openInformation(window.getShell(), "Info",
				sub2.get("h1", "default"));

		// Forces the application to save the preferences
		try {
			preferences.flush();

		} catch (BackingStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {

	}

	@Override
	public void dispose() {

	}

	@Override
	public void init(IWorkbenchWindow window) {
		this.window = window;

	}

}

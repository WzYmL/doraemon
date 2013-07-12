package org.salever.rcp.remoteSystem.client.net.core.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.salever.rcp.remoteSystem.client.net.core.Messages;
import org.salever.rcp.remoteSystem.client.net.core.NetCoreActivator;


/**
 * This class represents a preference page that is contributed to the
 * Preferences dialog. By subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows us to create a page
 * that is small and knows how to save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They are stored in the
 * preference store that belongs to the main plug-in class. That way,
 * preferences can be accessed directly via the preference store.
 */

public class ServerPreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	public ServerPreferencePage() {
		super(GRID);
		setPreferenceStore(NetCoreActivator.getDefault().getPreferenceStore());
		setDescription(Messages.ServerPreferencePage_DataServer);
	}

	/**
	 * Creates the field editors. Field editors are abstractions of the common
	 * GUI blocks needed to manipulate various types of preferences. Each field
	 * editor knows how to save and restore itself.
	 */
	public void createFieldEditors() {
		addField(new StringFieldEditor(PreferenceConstants.P_URL,
				Messages.ServerPreferencePage_URL, getFieldEditorParent()));
		// addField(new StringFieldEditor(PreferenceConstants.P_USERNAME,
		// Messages.ServerPreferencePage_Username, getFieldEditorParent()));
		// addField(new PwdStringFieldEditor(PreferenceConstants.P_PASSWORD,
		// Messages.ServerPreferencePage_Password, getFieldEditorParent()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

}
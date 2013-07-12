package org.salever.rcp.dbSystem.client.db.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.salever.rcp.dbSystem.client.db.DBCoreActivator;
import org.salever.rcp.dbSystem.client.db.DBPreferenceConstants;
import org.salever.rcp.dbSystem.client.db.Messages;

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

public class MySQLPreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	public MySQLPreferencePage() {
		super(GRID);
		setPreferenceStore(DBCoreActivator.getDefault().getPreferenceStore());
		setDescription(Messages.MySQLPreferencePage_DBConfig);
	}

	/**
	 * Creates the field editors. Field editors are abstractions of the common
	 * GUI blocks needed to manipulate various types of preferences. Each field
	 * editor knows how to save and restore itself.
	 */
	@Override
	public void createFieldEditors() {

		// addField(new StringFieldEditor(DBPreferenceConstants.P_INSTALL_DIR,
		// Messages.MySQLPreferencePage_DBDir, getFieldEditorParent()));
		// addField(new StringFieldEditor(DBPreferenceConstants.P_DRIVE_CLASS,
		// Messages.MySQLPreferencePage_DBDriver, getFieldEditorParent()));
		// addField(new StringFieldEditor(DBPreferenceConstants.P_DATABASE,
		// Messages.MySQLPreferencePage_database, getFieldEditorParent()));
		addField(new StringFieldEditor(DBPreferenceConstants.P_URL,
				Messages.MySQLPreferencePage_DB_URL, getFieldEditorParent()));
		addField(new StringFieldEditor(DBPreferenceConstants.P_USERNAME,
				Messages.MySQLPreferencePage_DBUsername, getFieldEditorParent()));
		addField(new PwdStringFieldEditor(DBPreferenceConstants.P_PASSWORD,
				Messages.MySQLPreferencePage_DBPassword, getFieldEditorParent()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	@Override
	public void init(IWorkbench workbench) {
	}

}
package org.salever.j2se.com.preferences;

import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.salever.j2se.com.ClientComPlugin;
import org.salever.j2se.com.Messages;


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

public class COMPreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	public COMPreferencePage() {
		super(GRID);
		setPreferenceStore(ClientComPlugin.getDefault().getPreferenceStore());
		setDescription(Messages.COMPreferencePage_desc);
	}

	/**
	 * Creates the field editors. Field editors are abstractions of the common
	 * GUI blocks needed to manipulate various types of preferences. Each field
	 * editor knows how to save and restore itself.
	 */
	public void createFieldEditors() {
		addField(new StringFieldEditor(PreferenceConstants.P_PORT_NAME,
				Messages.COMPreferencePage_comName, getFieldEditorParent()));

		addField(new IntegerFieldEditor(PreferenceConstants.P_BAUD_RATE,
				Messages.COMPreferencePage_baud, getFieldEditorParent()));
		addField(new ComboFieldEditor(PreferenceConstants.P_FOLLOW_CONTROL_IN,
				Messages.COMPreferencePage_followControlIn,
				PreferenceConstants.FOLLOW_CONTROL_IN_VALUES,
				getFieldEditorParent()));
		addField(new ComboFieldEditor(PreferenceConstants.P_FOLLOW_CONTROL_OUT,
				Messages.COMPreferencePage_followControlOut,
				PreferenceConstants.FOLLOW_CONTROL_OUT_VALUES,
				getFieldEditorParent()));
		addField(new ComboFieldEditor(PreferenceConstants.P_DATA_BITS,
				Messages.COMPreferencePage_dataBits,
				PreferenceConstants.DATA_BITS_VALUES, getFieldEditorParent()));
		addField(new ComboFieldEditor(PreferenceConstants.P_STOP_BITS,
				Messages.COMPreferencePage_stopBits,
				PreferenceConstants.STOP_BITS_VALUES, getFieldEditorParent()));
		addField(new ComboFieldEditor(PreferenceConstants.P_PARITY,
				Messages.COMPreferencePage_parity,
				PreferenceConstants.PARITY_VALUES, getFieldEditorParent()));
		addField(new IntegerFieldEditor(PreferenceConstants.P_READ_INTERVAL,
				Messages.COMPreferencePage_readInterval, getFieldEditorParent()));

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
package org.salever.rcp.tools.springxmlgenerator.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.salever.rcp.tools.springxmlgenerator.Activator;

/**
 * This class represents a preference page that
 * is contributed to the Preferences dialog. By 
 * subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows
 * us to create a page that is small and knows how to 
 * save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They
 * are stored in the preference store that belongs to
 * the main plug-in class. That way, preferences can
 * be accessed directly via the preference store.
 */

public class SpringXMLGeneratorPage
	extends FieldEditorPreferencePage
	implements IWorkbenchPreferencePage {

	public SpringXMLGeneratorPage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("Spring Tools: XML generator configuration");
	}
	
	/**
	 * Creates the field editors. Field editors are abstractions of
	 * the common GUI blocks needed to manipulate various types
	 * of preferences. Each field editor knows how to save and
	 * restore itself.
	 */
	public void createFieldEditors() {
		addField(new StringFieldEditor(PreferenceConstants.P_DEFAULT_XML_NAME, 
				"&Default XML Name:", getFieldEditorParent()));
//		addField(
//			new BooleanFieldEditor(
//				PreferenceConstants.P_USE_INTERFACE_NAME,
//				"&Use Interface name if the class is an implementation",
//				getFieldEditorParent()));
		addField(
			new BooleanFieldEditor(PreferenceConstants.P_OVERWRITE_EXIST_FILE, "Overwrite existing file ", getFieldEditorParent()));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}
	
	@Override
	public boolean isValid() {
		
		String defauleName = getPreferenceStore().getString(PreferenceConstants.P_DEFAULT_XML_NAME);
		if(defauleName == null){
			setErrorMessage("Default XML Name is invalid, should be *.xml");
			return false;
		}
		return super.isValid();
	}
	
}

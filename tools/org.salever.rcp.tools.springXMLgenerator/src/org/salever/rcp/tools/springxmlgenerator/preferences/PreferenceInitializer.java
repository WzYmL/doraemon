package org.salever.rcp.tools.springxmlgenerator.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.salever.rcp.tools.springxmlgenerator.Activator;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		store.setDefault(PreferenceConstants.P_OVERWRITE_EXIST_FILE, false);
		store.setDefault(PreferenceConstants.P_DEFAULT_XML_NAME, "springContext.xml");
		store.setDefault(PreferenceConstants.P_USE_INTERFACE_NAME,
				true);
	}

}

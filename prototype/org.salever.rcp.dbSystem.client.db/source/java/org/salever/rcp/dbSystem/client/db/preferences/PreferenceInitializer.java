package org.salever.rcp.dbSystem.client.db.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.salever.rcp.dbSystem.client.db.DBCoreActivator;
import org.salever.rcp.dbSystem.client.db.DBPreferenceConstants;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#
	 * initializeDefaultPreferences()
	 */
	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = DBCoreActivator.getDefault()
				.getPreferenceStore();
		store.setDefault(DBPreferenceConstants.P_DRIVE_CLASS,
				"com.mysql.jdbc.Driver");
		store.setDefault(DBPreferenceConstants.P_DATABASE, "readid_data");
		store.setDefault(DBPreferenceConstants.P_URL,
				"jdbc:mysql://localhost:3306/readid_data");
		store.setDefault(DBPreferenceConstants.P_USERNAME, "root");
		store.setDefault(DBPreferenceConstants.P_PASSWORD, "readid702");
		store.setDefault(DBPreferenceConstants.P_INSTALL_DIR,
				"C:/Program Files/MySQL/MySQL Server 5.0");
	}

}

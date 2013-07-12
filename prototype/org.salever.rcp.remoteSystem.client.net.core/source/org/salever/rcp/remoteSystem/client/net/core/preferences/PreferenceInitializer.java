package org.salever.rcp.remoteSystem.client.net.core.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.salever.rcp.remoteSystem.client.net.core.NetCoreActivator;


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
	public void initializeDefaultPreferences() {
		IPreferenceStore store = NetCoreActivator.getDefault()
				.getPreferenceStore();
		store.setDefault(PreferenceConstants.P_URL, "http://localhost:8080");
		store.setDefault(PreferenceConstants.P_USERNAME, "admin");
		store.setDefault(PreferenceConstants.P_PASSWORD, "admin123");
		store.setDefault(PreferenceConstants.P_SERVER_NAME,
				"com.readidtech.kgarten.server/remoting");

	}
}

package org.salever.j2se.com.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.salever.j2se.com.ClientComPlugin;


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
		IPreferenceStore store = ClientComPlugin.getDefault()
				.getPreferenceStore();
		store.setDefault(PreferenceConstants.P_BAUD_RATE, 115200);
		store.setDefault(PreferenceConstants.P_DATA_BITS, "8");
		store.setDefault(PreferenceConstants.P_FOLLOW_CONTROL_IN, "None");
		store.setDefault(PreferenceConstants.P_FOLLOW_CONTROL_OUT, "None");
		store.setDefault(PreferenceConstants.P_PARITY, "None");
		store.setDefault(PreferenceConstants.P_PORT_NAME, "COM3");
		store.setDefault(PreferenceConstants.P_STOP_BITS, "1");
		store.setDefault(PreferenceConstants.P_READ_INTERVAL, 1500);
	}

}

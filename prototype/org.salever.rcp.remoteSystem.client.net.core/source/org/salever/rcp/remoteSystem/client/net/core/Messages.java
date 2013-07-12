package org.salever.rcp.remoteSystem.client.net.core;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.salever.rcp.remoteSystem.client.net.core.messages"; //$NON-NLS-1$
	public static String ServerCategoryPage_Dataserver;
	public static String ServerPreferencePage_DataServer;
	public static String ServerPreferencePage_Password;
	public static String ServerPreferencePage_URL;
	public static String ServerPreferencePage_Username;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}

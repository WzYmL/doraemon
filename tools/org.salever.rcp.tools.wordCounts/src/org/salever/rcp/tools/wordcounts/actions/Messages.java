package org.salever.rcp.tools.wordcounts.actions;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.salever.rcp.tools.wordcounts.actions.messages"; //$NON-NLS-1$
	public static String CountAction_1;
	public static String OpenAction_1;
	public static String OpenAndCountAction_1;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}

package org.salever.j2se.com;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.salever.j2se.com.messages"; //$NON-NLS-1$
	public static String COMCategoryPreferencePage_desc;
	public static String COMPreferencePage_baud;
	public static String COMPreferencePage_comName;
	public static String COMPreferencePage_dataBits;
	public static String COMPreferencePage_desc;
	public static String COMPreferencePage_followControlIn;
	public static String COMPreferencePage_followControlOut;
	public static String COMPreferencePage_parity;
	public static String COMPreferencePage_readInterval;
	public static String COMPreferencePage_stopBits;
	public static String SerialHelper_comConnected;
	public static String SerialHelper_comOpenFail;
	public static String SerialHelper_comOpenOK;
	public static String SerialHelper_comReconnect;
	public static String SerialHelper_notify;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}

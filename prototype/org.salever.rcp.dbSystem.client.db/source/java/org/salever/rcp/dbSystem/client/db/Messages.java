package org.salever.rcp.dbSystem.client.db;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.salever.rcp.dbSystem.client.db.messages"; //$NON-NLS-1$
	public static String DBCategoryPage_desc;
	public static String MySQLPreferencePage_database;
	public static String MySQLPreferencePage_DB_URL;
	public static String MySQLPreferencePage_DBConfig;
	public static String MySQLPreferencePage_DBDir;
	public static String MySQLPreferencePage_DBDriver;
	public static String MySQLPreferencePage_DBPassword;
	public static String MySQLPreferencePage_DBUsername;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}

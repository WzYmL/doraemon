package org.salever.rcp.dbSystem.client.db;

import java.util.prefs.Preferences;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.salever.rcp.dbSystem.client.db.util.ProjectUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class DBCoreActivator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.salever.rcp.dbSystem.client.db.core"; //$NON-NLS-1$

	public static final String PREF_ID = "org.salever.rcp.dbSystem.client.db.preferences.DBPreferencePage"; //$NON-NLS-1$

	// The shared instance
	private static DBCoreActivator plugin;

	/**
	 * 
	 * @return
	 */
	public static String getDatabase() {
		IPreferenceStore preferenceStore = getDefault().getPreferenceStore();
		String address = preferenceStore.getString(DBPreferenceConstants.P_URL);
		try {
			address = address.substring(address.lastIndexOf("/") + 1,
					address.length());
		} catch (Exception e) {
			return "readid_data";
		}
		return address;
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static DBCoreActivator getDefault() {
		return plugin;
	}

	/**
	 * 
	 * @return
	 */
	public static String getHost() {
		IPreferenceStore preferenceStore = getDefault().getPreferenceStore();
		String address = preferenceStore.getString(DBPreferenceConstants.P_URL);
		try {
			address = address.substring("jdbc:mysql://".length());
			address = address.substring(0, address.indexOf(":"));
		} catch (Exception e) {
			return "localhost";
		}
		return address;
	}

	private ApplicationContext ctx;

	/**
	 * The constructor
	 */
	public DBCoreActivator() {
	}

	/**
	 * 检查数据库配置
	 * 
	 * @return
	 */
	public boolean checkPreference() {
		IPreferenceStore ps = getPreferenceStore();
		String drive = ps.getString(DBPreferenceConstants.P_DRIVE_CLASS);
		String url = ps.getString(DBPreferenceConstants.P_URL);
		String username = ps.getString(DBPreferenceConstants.P_USERNAME);
		String password = ps.getString(DBPreferenceConstants.P_PASSWORD);
		return !(StringUtils.isEmpty(drive) || StringUtils.isEmpty(url)
				|| StringUtils.isEmpty(username) || StringUtils
				.isEmpty(password));
	}

	/**
	 * 获取Spring bean
	 * 
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	public <T> T getBean(Class<T> clazz) {

		if (ctx == null) {
			initializeApplicationContext();
		}

		if (ctx != null) {
			return ctx.getBean(clazz);
		}
		return null;
	}

	public boolean initializeApplicationContext() {

		IPreferenceStore ps = getPreferenceStore();
		String drive = ps.getString(DBPreferenceConstants.P_DRIVE_CLASS);
		String url = ps.getString(DBPreferenceConstants.P_URL);
		String username = ps.getString(DBPreferenceConstants.P_USERNAME);
		String password = ps.getString(DBPreferenceConstants.P_PASSWORD);

		Preferences userRoot = Preferences.userRoot();
		userRoot.put(DBPreferenceConstants.P_DRIVE_CLASS, drive);
		userRoot.put(DBPreferenceConstants.P_URL, url);
		userRoot.put(DBPreferenceConstants.P_USERNAME, username);
		userRoot.put(DBPreferenceConstants.P_PASSWORD, password);
		ClassLoader oldLoader = Thread.currentThread().getContextClassLoader();
		try {
			Thread.currentThread().setContextClassLoader(
					getDefault().getClass().getClassLoader());
			// getDefault().getClass().getClassLoader().loadClass(drive);
			this.ctx = new FileSystemXmlApplicationContext(
					ProjectUtil.toFullPath("config/applicationContext.xml"));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Thread.currentThread().setContextClassLoader(oldLoader);
		}

		return ctx != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		// initializeApplicationContext();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}
}

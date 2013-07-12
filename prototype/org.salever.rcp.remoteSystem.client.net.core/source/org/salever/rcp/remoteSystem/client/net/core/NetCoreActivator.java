package org.salever.rcp.remoteSystem.client.net.core;

import java.util.prefs.Preferences;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.salever.rcp.remoteSystem.client.net.core.preferences.PreferenceConstants;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class NetCoreActivator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.salever.rcp.remoteSystem.client.net.core"; //$NON-NLS-1$

	// The shared instance
	private static NetCoreActivator plugin;

	private ApplicationContext ctx;

	/**
	 * The constructor
	 */
	public NetCoreActivator() {
	}

	public boolean initializeApplicationContext() {

		IPreferenceStore ps = getPreferenceStore();
		String url = ps.getString(PreferenceConstants.P_URL);
		String serverName = ps.getString(PreferenceConstants.P_SERVER_NAME);
		String username = ps.getString(PreferenceConstants.P_USERNAME);
		String password = ps.getString(PreferenceConstants.P_PASSWORD);

		if (url == null) {
			throw new IllegalArgumentException("Server URL is empty");
		}

		if (!url.endsWith("/")) {
			url = url + "/" + serverName;
		} else {
			url = url + serverName;
		}

		Preferences userRoot = Preferences.userRoot();
		userRoot.put(PreferenceConstants.P_URL, url);
		userRoot.put(PreferenceConstants.P_USERNAME, username);
		userRoot.put(PreferenceConstants.P_PASSWORD, password);

		ClassLoader oldLoader = Thread.currentThread().getContextClassLoader();
		try {
			Thread.currentThread().setContextClassLoader(
					getDefault().getClass().getClassLoader());
			this.ctx = new FileSystemXmlApplicationContext(
					ProjectUtil.toFullPath("conf/applicationContext.xml"));

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
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static NetCoreActivator getDefault() {
		return plugin;
	}

	public <T> T getBean(Class<T> clazz) {
		if (ctx != null) {
			return ctx.getBean(clazz);
		}
		return null;
	}
}

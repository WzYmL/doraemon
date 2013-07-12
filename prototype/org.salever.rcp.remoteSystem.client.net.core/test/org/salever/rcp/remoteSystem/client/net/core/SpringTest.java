package org.salever.rcp.remoteSystem.client.net.core;

import java.util.prefs.Preferences;

import org.salever.rcp.remoteSystem.client.net.core.preferences.PreferenceConstants;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


public abstract class SpringTest {

	private static ApplicationContext context = null;

	private static String springFilePath = "/test/com/readidtech/common/client/net/core/conf/applicationContext.xml";

	public void setSpringFilePath(String springFilePath) {
		SpringTest.springFilePath = springFilePath;
	}

	public static ApplicationContext getContext() {
		if (context == null) {

			Preferences userRoot = Preferences.userRoot();
			userRoot.put(PreferenceConstants.P_URL,
					"http://localhost:8080/com.readidtech.kgarten.server/remoting");
			userRoot.put(PreferenceConstants.P_USERNAME, "admin");
			userRoot.put(PreferenceConstants.P_PASSWORD, "admin123");
			context = new FileSystemXmlApplicationContext(springFilePath);
		}
		return context;
	}

	public <T> T getBean(Class<T> clazz) {
		ApplicationContext context = getContext();
		return (T) context.getBean(clazz);
	}
}

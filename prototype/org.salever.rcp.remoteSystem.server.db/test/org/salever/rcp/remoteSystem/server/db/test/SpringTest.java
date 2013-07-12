package org.salever.rcp.remoteSystem.server.db.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public abstract class SpringTest {

	private static ApplicationContext context = null;
	private static String springFilePath = "test/applicationContext.xml";

	public void setSpringFilePath(String springFilePath) {
		SpringTest.springFilePath = springFilePath;
	}

	public static ApplicationContext getContext() {
		if (context == null) {
			context = new FileSystemXmlApplicationContext(springFilePath);
		}
		return context;
	}

	public <T> T getBean(Class<T> clazz) {
		ApplicationContext context = getContext();
		return (T) context.getBean(clazz);
	}
}

package org.salever.rcp.remoteSystem.server.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InitListener implements ServletContextListener {

	private static final long serialVersionUID = 8920435030558656346L;

	private static final Log log = LogFactory.getLog(InitListener.class);

	public void contextInitialized(ServletContextEvent event) {
		log.debug("Context started");
	}

	public void contextDestroyed(ServletContextEvent event) {

	}
}
/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.talend.router.ftp.runner;

import java.io.File;
import java.net.URL;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.ssl.SslConfigurationFactory;
import org.apache.ftpserver.usermanager.ClearTextPasswordEncryptor;
import org.apache.ftpserver.usermanager.impl.PropertiesUserManager;

/**
 * @version $Revision: 24 $
 */
public class FtpsServerRunner {

	protected static FtpServer ftpServer;
	private static int port = 23000;

	public static void startServer() throws Exception {
		System.out.println("Starting FTP Server...");
		initFtpServer();
		ftpServer.start();
		System.out.println("Starting FTP Server done.");
	}

	public static void shutdownServer() throws Exception {
		System.out.println("Stopping FTP Server...");
		try {
			ftpServer.stop();
			ftpServer = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Stopping FTP Server done.");
	}

	public static void initFtpServer() throws Exception {
		FtpServerFactory serverFactory = new FtpServerFactory();

		ListenerFactory factory = new ListenerFactory();

		// set the port of the listener
		factory.setPort(port);

		/*
		 * Start to configure FTPS
		 * Just comments these lines if you don't 
		 * want to use FTPS
		 */
		// define SSL configuration
		SslConfigurationFactory ssl = new SslConfigurationFactory();
		ssl.setKeystoreFile(new File("ftpserver.jks"));
		ssl.setKeystorePassword("password");

		// set the SSL configuration for the listener
		factory.setSslConfiguration(ssl.createSslConfiguration());
		factory.setImplicitSsl(true);
		/*
		 * End of configuring FTPS
		 */

		// replace the default listener
		serverFactory.addListener("default", factory.createListener());

		URL url = loadResourceAsURL("users.properties");
		UserManager uman = new PropertiesUserManager(
				new ClearTextPasswordEncryptor(), url, "admin");

		serverFactory.setUserManager(uman);

		// start the server
		ftpServer = serverFactory.createServer();
	}

	public static URL loadResourceAsURL(String name) {
		URL url = null;

		ClassLoader contextClassLoader = Thread.currentThread()
				.getContextClassLoader();
		if (contextClassLoader != null) {
			url = contextClassLoader.getResource(name);
		}
		if (url == null) {
			url = FtpsServerRunner.class.getClassLoader().getResource(name);
		}

		return url;
	}

	public static void main(String[] args) {
		try {
			FtpsServerRunner.startServer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

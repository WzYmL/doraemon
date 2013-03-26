/*
 * Logger.java
 *
 * Copyright (c) 2001 GlobaLoop LTD., Joseph Hartal, Ze'ev Bubis.
 * All Rights Reserved.
 *
 * You may study, use, modify, and distribute this software for any
 * purpose provided that this copyright notice appears in all copies.
 *
 * This software is provided WITHOUT WARRANTY either expressed or
 * implied.
 *
 */

package org.salever.j2se.common.thread;

public class SimpleLogger implements ILogger {
	private static ILogger _instance = new SimpleLogger();

	public static ILogger getLogger() {
		return _instance;
	}

	public void critical(String message, Throwable tr) {
		System.out.println("critical : " + message);
		tr.printStackTrace();
	}

	public void critical(String message) {
		System.out.println("critical : " + message);
	}

	public void error(String message, Throwable tr) {
		System.out.println("error : " + message);
		tr.printStackTrace();
	}

	public void error(String message) {
		System.out.println("error : " + message);
	}

	public void warning(String message, Throwable tr) {
		System.out.println("warning : " + message);
		tr.printStackTrace();
	}

	public void warning(String message) {
		System.out.println("warning : " + message);
	}

	public void notice(String message) {
		System.out.println("notice : " + message);
	}

	public void trace(String message) {
		System.out.println("trace : " + message);
	}

	public void debug(String message, Throwable tr) {
		System.out.println("debug : " + message);
		tr.printStackTrace();
	}

	public void debug(String message) {
		System.out.println("debug : " + message);
	}

}

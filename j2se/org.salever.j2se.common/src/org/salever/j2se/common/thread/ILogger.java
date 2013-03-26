/*
 * ILogger.java
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

public interface ILogger {
	public void critical(String message, Throwable tr);

	public void critical(String message);

	public void error(String message, Throwable tr);

	public void error(String message);

	public void warning(String message, Throwable tr);

	public void warning(String message);

	public void notice(String message);

	public void trace(String message);

	public void debug(String message, Throwable tr);

	public void debug(String message);

}

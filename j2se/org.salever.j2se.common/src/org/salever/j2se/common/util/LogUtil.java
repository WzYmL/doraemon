/*******************************************************************************
 * Copyright (c) 2010 liXiaopeng. All rights reserved. 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *
 * Contributors:
 *     LiXiaopeng - initial API and implementation
 *
 * Create on 2011-2-22 下午01:46:44
 *******************************************************************************/
package org.salever.j2se.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author
 */
public class LogUtil {

	private static final DateFormat format = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	/**
	 * 
	 * @param message
	 */
	public static void info(String message) {
		System.out.println(format.format(Calendar.getInstance().getTime())
				+ ": " + message);
	}
	
	/**
	 * 
	 * @param message
	 */
	public static void error(String message) {
		System.err.println(format.format(Calendar.getInstance().getTime())
				+ ": " + message);
	}
}

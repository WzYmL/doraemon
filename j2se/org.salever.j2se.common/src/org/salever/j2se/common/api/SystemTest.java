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
 * Create on Jul 24, 2012 2:42:21 PM
 *******************************************************************************/
package org.salever.j2se.common.api;

import java.io.Console;
import java.util.Map;
import java.util.Properties;

/**
 * @author LiXP
 * 
 */
public class SystemTest {

	public static void main(String[] args) {
		Properties prop = System.getProperties();
		System.out.println(prop);

		String property = System.getProperty("user.home");
		System.out.println("user.home:" + property);

		System.setProperty("user.home", "c:/");
		property = System.getProperty("user.home");
		System.out.println("user.home:" + property);

		property = System.getProperty("user.dir");
		System.out.println("user.dir:" + property);

		System.setProperty("user.dir", "c:/");
		property = System.getProperty("user.dir");
		System.out.println("user.dir:" + property);

		property = System.getProperty("user.newArg");
		System.out.println("user.newArg:" + property);

		System.setProperty("user.newArg", "c:/");
		property = System.getProperty("user.newArg");
		System.out.println("user.newArg:" + property);

		Map<String, String> env = System.getenv();
		System.out.println(env);

		Console console = System.console();
		System.out.println(console);

		int size = 10000000;
		int[] sources = new int[size];
		int[] destination = new int[size];

		long nanoTime1 = System.nanoTime();
		long timeMillis1 = System.currentTimeMillis();
		System.out.println("System.nanoTime():" + nanoTime1);
		System.out.println("System.currentTimeMillis(): " + timeMillis1);

		for (int index = 0; index < size; index++) {
			destination[index] = sources[index];
		}

		long nanoTime2 = System.nanoTime();
		long timeMillis2 = System.currentTimeMillis();
		System.out.println("User array copy takes : " + (nanoTime2 - nanoTime1)
				+ " in ns.");
		System.out.println("User array copy takes : "
				+ (timeMillis2 - timeMillis1) + " in ms");

		System.arraycopy(sources, 0, destination, 0, size);

		long nanoTime3 = System.nanoTime();
		long timeMillis3 = System.currentTimeMillis();
		System.out.println("System array copy takes : "
				+ (nanoTime3 - nanoTime2) + " in ns.");
		System.out.println("System array copy takes : "
				+ (timeMillis3 - timeMillis2) + " in ms");

		int hashCode = System.identityHashCode(new Object());
		System.out.println(Integer.toHexString(hashCode));
	}
}

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
 * Create on Mar 14, 2012 2:20:53 PM
 *******************************************************************************/
package org.salever.j2se.common.java6;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 * @author LiXP
 * 
 */
public class DesktopTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		if (!Desktop.isDesktopSupported()) {
			System.out.println("Desktop is not supported.");
			System.exit(-1);
		}

		Desktop desktop = Desktop.getDesktop();
		File dir = new File(".");
		File[] files = dir.listFiles();
		for (File file : files) {
			if (!desktop.isSupported(Desktop.Action.OPEN)) {
				continue;
			}
			System.out.println("Open '" + file.getName() + "'? [YES/NO]:");
			String line = System.console().readLine();
			if ("YES".equals(line)) {
				System.out.println("Opening " + file.getName() + "......");
				try {
					desktop.open(file);
				} catch (IOException e) {
					System.out.println("Unable to open " + file.getName());
				}
			}
		}

	}

}

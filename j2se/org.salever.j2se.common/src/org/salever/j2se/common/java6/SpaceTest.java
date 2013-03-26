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
 * Create on Mar 23, 2012 3:15:25 PM
 *******************************************************************************/
package org.salever.j2se.common.java6;

import java.io.File;

/**
 * @author LiXP
 * 
 */
public class SpaceTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File[] roots = File.listRoots();
		for (File root : roots) {
			System.out.printf("%s has %,d / %,d free%n", root.getPath(),
					root.getUsableSpace(), root.getTotalSpace());
		}

	}

}
